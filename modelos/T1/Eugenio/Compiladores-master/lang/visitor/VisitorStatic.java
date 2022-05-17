/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import lang.ast.*;
import lang.visitor.types.*;

public class VisitorStatic extends Visitor {
    private HashMap<Funcao, StyFunc> funcs;
    private HashMap<String, StyData> dates;
    private Stack<HashMap<String,Stype>> vars;
    private Stack<Stype> types;
    private LvalueUnic l;
    private ArrayList<Stype> retornos;
    private int numRetornos;

    public VisitorStatic(){
        funcs = new HashMap<Funcao, StyFunc>();
        dates = new HashMap<String, StyData>();
        types = new Stack<Stype>();
        vars = new Stack<HashMap<String,Stype>>();
        l=null;
        retornos=null;
        numRetornos=0;
    }
    
    public HashMap<String, StyData> getDates(){
        return this.dates;
    }

    public HashMap<Funcao, StyFunc> getFuncs(){
        return this.funcs;
    }
    
    public Stype getType(Exp e, HashMap<String, Stype> v){
        vars.add(v);
        e.accept(this);
        vars.pop();
        return types.pop();
    }

    private void addVar(LvalueUnic key, Stype value, int line, int column){
        String id = key.getId().getID();
        //se nao é vetor nem data:
        if(key.getLvalues().isEmpty()){
            if(vars.peek().containsKey(id)){
                Stype s = vars.peek().get(id);
                if(!s.match(value)){
                    System.out.println("\nErro: " + "(" + line + ", " + column +  ") Variavel " + key.getId().getID() + " nao pode mudar de tipo durante a execução");
                    System.exit(0);
                }
            }else{
                if(!value.match(StyNull.newStyNull())){
                    vars.peek().put(key.getId().getID(), value);
                }else{
                    System.out.println("\nErro: " + "(" + line + ", " + column +  ") Variavel " + key.getId().getID() + " nao pode ser inicializada como null");
                    System.exit(0);
                }
            }
        }else{
            //sendo vetor ou data:
            if(vars.peek().containsKey(id)){
                Stype s = vars.peek().get(id);
                for(Lvalue l : key.getLvalues()){
                    if(l instanceof LvalueArray){
                        ((LvalueArray)l).getExp().accept(this);
                        if(!types.pop().match(StyInt.newStyInt())){
                            System.out.println("\nErro: " + "(" + line + ", " + column +  ") Acesso a posição de vetor so aceita tipo inteiro ");
                            System.exit(0);
                        }
                        if(s instanceof StyArr){
                            s=((StyArr)s).getArg();
                        }else{
                            System.out.println("\nErro: " + "(" + line + ", " + column +  ") Acesso inválido a variável " + id);
                            System.exit(0);
                        }
                    }else if(l instanceof LvalueSelect){
                        if(s instanceof StyData){
                            if(((StyData)s).getCampos().containsKey(((LvalueSelect)l).getID().getID())){
                                s=((StyData)s).getCampos().get(((LvalueSelect)l).getID().getID());
                            }else{
                                System.out.println("\nErro: " + "(" + line + ", " + column +  ") Acesso inválido a variável " + id);
                                System.exit(0);
                            }
                        }else{
                            System.out.println("\nErro: " + "(" + line + ", " + column +  ") Acesso inválido a variável " + id);
                            System.exit(0);
                        }    
                    }
                }
                if(!s.match(value)){
                    if(!(s.match(StyInt.newStyInt()) || s.match(StyFloat.newStyFloat()) || s.match(StyBool.newStyBool())) && value.match(StyNull.newStyNull())){
                        //se s nao for tipo primitivo, pode setar como null
                    }else{
                        System.out.println("\nErro: " + "(" + line + ", " + column +  ") Atribuicao inválida de valor\n");
                        System.exit(0);
                    }
                }
            }else{
                System.out.println("\nErro: " + "(" + line + ", " + column +  ") Acesso inválido a variável " + id);
                System.exit(0);
            }
        }
    }

    public void visit(Prog prog) {
        ArrayList<Data> d = prog.getDatas();
        for(Data i:d){
            //verifica se existem 2 data de ids iguais
            if(!dates.containsKey(i.getId().getID())){
                dates.put(i.getId().getID(), new StyData(i.getId().getID(),new HashMap<String, Stype>()));
            }else{
                System.out.println("\nErro: " + "(" + i.getLine() + ", " + i.getColumn() +  ") Data de nome " + i.getId().getID() + " declarada 2 vezes!");
                System.exit(0);
            }
        }
        for(Data i:d){
            //seta os tipos de cada campo de cada data
            i.accept(this);
        }
        
        
        ArrayList<Func> f = prog.getFuncs();
        Func main = null;
        for(Func i:f){
            //pegando tipos de parametros
            ArrayList<Stype> types = new ArrayList<Stype>();
            for(Type t : i.getTypeParams()){
                t.accept(this);
                types.add(this.types.pop());
            }

            //pegando tipos de retornos 
            ArrayList<Stype> ret = new ArrayList<Stype>();
            for(Type t : i.getTypeReturns()){
                t.accept(this);
                ret.add(this.types.pop());
            }
            
            Funcao funcao = new Funcao(i.getID().getID(),types); 
            StyFunc retorno = new StyFunc(ret);
            if(!funcs.containsKey(funcao)){
                funcs.put(funcao, retorno);
            }else{
                System.out.println("\nErro: " + "(" + i.getLine() + ", " + i.getColumn() +  ") Funcao " + i.getID().getID() + " declarada 2 vezes com os mesmos tipos de parametros!");
                System.exit(0);
            }
            if(i.getID().getID().equals("main")){
                if(i.getTypeParams().size()==0){
                    if(main==null){
                        main = i;
                    }else{
                        System.out.println("\nErro (" + i.getLine() + ", " + i.getColumn() + ") Programa contém mais de uma main\n");
                        System.exit(0);
                    }
                }else{
                    System.out.println("\nErro (" + i.getLine() + ", " + i.getColumn() + ") main não deve conter parâmetros\n");
                    System.exit(0);
                }
            }
        }
        if(main==null){
            System.out.println("\nErro: Não foi encontrada a função main\n");
            System.exit(0);
        }
        for(Func i:f){
            i.accept(this);
        }
        
    }

    public void visit(Data data){
        HashMap<String,Stype> h = dates.get(data.getId().getID()).getCampos();
        for(Decl d : data.getDecl()){
            if(h.containsKey(d.getId().getID())){
                System.out.println("\nErro: " + "(" + data.getLine() + ", " + data.getColumn() +  ") Data " + data.getId().getID() + " contém 2 campos de nome " + d.getId().getID() +  "!");
                System.exit(0);
            }else{
                d.getType().accept(this);
                h.put(d.getId().getID(), this.types.pop());
            }
        }
    }
    
    public void visit(Aexp aexp) {
        aexp.getMexps().get(0).accept(this);
        Stype current = types.pop();
        if(aexp.getMexps().size()>1){
            if(!( (current.match(StyInt.newStyInt())) || (current.match(StyFloat.newStyFloat())) )){
                System.out.println("\nErro: " + "(" + aexp.getLine() + ", " + aexp.getColumn() +  ") Operação de +/- so pode ser feita com operandos Int ou Float" );
                System.exit(0);
            }
        }
        for(int i=1;i<aexp.getMexps().size();i++){
            aexp.getMexps().get(i).accept(this);
            if(!current.match(types.pop())){
                System.out.println("\nErro: " + "(" + aexp.getLine() + ", " + aexp.getColumn() +  ") Operação de +/- so pode ser feita entre operandos do mesmo tipo" );
                System.exit(0);
            }
        }
        types.add(current);
    }

    public void visit(Attr attr) {
        attr.getExp().accept(this);
        addVar(attr.getLvalue(), types.pop() , attr.getLine(), attr.getColumn());
    }

    public void visit(Decl decl) {
        //nao vai ser chamado
    }

    public void visit(Exp exp) {
        if(exp.getRexps().size()==1){
            //se qtd de rexp =1, basta empilhar o resultado da propria rexp
            exp.getRexps().get(0).accept(this);
        }else{
            //se qtd de rexp>1, todos os itens de rexp devem ser do tipo bool e o resultado da saida tbm é bool
            for(Rexp r : exp.getRexps()){
                r.accept(this);
                if(!(types.pop().match(StyBool.newStyBool()))){
                    System.out.println("\nErro: ("+ exp.getLine() + ", " + exp.getColumn() + ") Operacao de and so aceita operandos bool!!!\n");
                    System.exit(0);
                }
            }
            types.add(StyBool.newStyBool());
        }
    }

    public void visit(Func func) {
        Boolean retornou = false;
        HashMap<String, Stype> localVars = new HashMap<String, Stype>();
        ArrayList<Stype> params = new ArrayList<Stype>();
        //add os parametros em variaveis
        for (int i = 0; i < func.getIdsParams().size(); i++) {
            func.getTypeParams().get(i).accept(this);
            Stype s = this.types.pop();
            localVars.put(func.getIdsParams().get(i).getID(), s);
            params.add(s);
        }
        StyFunc f = funcs.get(new Funcao(func.getID().getID(), params));
        retornos = f.getRet();
        vars.push(localVars);
        for(Cmd c:func.getCmds()){
            if(c instanceof If){
                numRetornos=0;
                c.accept(this);
                if(numRetornos==2){
                    //se ouve retorno no if e no else
                    retornou=true;
                    break;
                }
            }else if(c instanceof Iterate){
                //nao ha garantia que o iterate ira executar pelo menos uma vez, logo nao há como garantir o retorno dentro dele, se este existir
                c.accept(this);
            }else if(c instanceof Return){
                c.accept(this);
                retornou=true;
                break;
            }else if(c instanceof Attr){
                c.accept(this);
            }else if(c instanceof FunctionCall){
                c.accept(this);
            }else if(c instanceof Print){
                c.accept(this);
            }else if(c instanceof Read){
                c.accept(this);
            }
        }
        if(!retornou && retornos.size()>0){
            System.out.println("\nErro: ("+ func.getLine() + ", " + func.getColumn() + ") Existe algum caminho no qual não há retorno na funcao " + func.getID().getID() + "\n");
            System.exit(0);
        }
        f.setVars(vars.pop());
        retornos=null;
    }

    //precisa de criar as variaveis antes
    public void visit(FunctionCall functioncall) {
        ArrayList<Stype> params = new ArrayList<Stype>();
        for(Exp e : functioncall.getExps()){
            e.accept(this);
            params.add(types.pop());
        }
        StyFunc fu = funcs.get(new Funcao(functioncall.getId().getID(), params));
        if(fu==null){
            System.out.println("\nErro: ("+ functioncall.getLine() + ", " + functioncall.getColumn() + ") Chamada de funcao " + functioncall.getId().getID() + " inexistente!!!\n");
            System.exit(0);
        }else{
            if(functioncall.getLvalues().size() == fu.getRet().size()){
                for (int i=0;i<fu.getRet().size();i++){
                    addVar((LvalueUnic)functioncall.getLvalues().get(i), fu.getRet().get(i), functioncall.getLine(), functioncall.getColumn());
                }
            }else{
                System.out.println("\nErro: ("+ functioncall.getLine() + ", " + functioncall.getColumn() + ") Funcao " + functioncall.getId().getID() + " possui numero de retornos diferente do solicitado\n");
                System.exit(0);
            }
        }
    }

    public void visit(ID id) {
        //nao vai ser visitado
    }

    public void visit(If i) {
        int retornos = 0;
        numRetornos=0;
        i.getCond().accept(this);
        if(types.pop().match(StyBool.newStyBool())){
            for(Cmd c:i.getThen()){
                if(c instanceof If){
                    c.accept(this);
                    if(numRetornos==2){
                        //se ouve retorno no if e no else
                        retornos++;
                        break;
                    }
                }else if(c instanceof Iterate){
                    //nao ha garantia que o iterate ira executar pelo menos uma vez, logo nao há como garantir o retorno dentro dele, se este existir
                    c.accept(this);
                }else if(c instanceof Return){
                    c.accept(this);
                    retornos++;
                    break;
                }else if(c instanceof Attr){
                    c.accept(this);
                }else if(c instanceof FunctionCall){
                    c.accept(this);
                }else if(c instanceof Print){
                    c.accept(this);
                }else if(c instanceof Read){
                    c.accept(this);
                }
            }
            for(Cmd c:i.getElse()){
                if(c instanceof If){
                    c.accept(this);
                    if(numRetornos==2){
                        //se ouve retorno no if e no else
                        retornos++;
                        break;
                    }
                }else if(c instanceof Iterate){
                    //nao ha garantia que o iterate ira executar pelo menos uma vez, logo nao há como garantir o retorno dentro dele, se este existir
                    c.accept(this);
                }else if(c instanceof Return){
                    c.accept(this);
                    retornos++;
                    break;
                }else if(c instanceof Attr){
                    c.accept(this);
                }else if(c instanceof FunctionCall){
                    c.accept(this);
                }else if(c instanceof Print){
                    c.accept(this);
                }else if(c instanceof Read){
                    c.accept(this);
                }
            }
            numRetornos=retornos;
        }else{
            System.out.println("\nErro: ("+ i.getLine() + ", " + i.getColumn() + ") Condicao do if deve ser do tipo bool\n");
            System.exit(0);
        }
    }

    public void visit(Iterate iterate) {
        iterate.getCond().accept(this);
        if(types.pop().match(StyInt.newStyInt())){
            for(Cmd c: iterate.getLoop()){
                if(c instanceof If){
                    c.accept(this);
                    if(numRetornos==2){
                        //se ouve retorno no if e no else
                        break;
                    }
                }else if(c instanceof Iterate){
                    //nao ha garantia que o iterate ira executar pelo menos uma vez, logo nao há como garantir o retorno dentro dele, se este existir
                    c.accept(this);
                }else if(c instanceof Return){
                    c.accept(this);
                    break;
                }else if(c instanceof Attr){
                    c.accept(this);
                }else if(c instanceof FunctionCall){
                    c.accept(this);
                }else if(c instanceof Print){
                    c.accept(this);
                }else if(c instanceof Read){
                    c.accept(this);
                }
            }
        }else{
            System.out.println("\nErro: ("+ iterate.getLine() + ", " + iterate.getColumn() + ") Condicao do iterate deve ser do tipo Int\n");
            System.exit(0);
        }
    }

    public void visit(LvalueArray lvaluearray) {
        //nao vai ser chamado
    }

    public void visit(LvalueSelect lvalueselect) {
        //nao vai ser chamado
    }

    public void visit(LvalueUnic lvalueunic) {
        l = lvalueunic;
    }

    public void visit(Mexp mexp) {
        mexp.getSexps().get(0).accept(this);
        Stype current = types.pop();
        if(mexp.getSexps().size()>1){
            if(!( (current.match(StyInt.newStyInt())) || (current.match(StyFloat.newStyFloat())) )){
                System.out.println("\nErro: ("+ mexp.getLine() + ", " + mexp.getColumn() + ") Operacao de multiplicacao, divisao e modulo so aceita operandos de tipo Int ou Float!!!\n");
                System.exit(0);
            }
        }
        ArrayList<String> signals = mexp.getSignals();
        for(int i=1;i<mexp.getSexps().size();i++){
            mexp.getSexps().get(i).accept(this);
            if(signals.get(i-1)=="%"){
                if(!( (types.pop().match(StyInt.newStyInt())) && (current.match(StyInt.newStyInt())) )){
                    System.out.println("\nErro: ("+ mexp.getLine() + ", " + mexp.getColumn() + ") Operacao de modulo (%) so aceita operandos Int!!!\n");
                    System.exit(0);
                }
            }else{
                if(!current.match(types.pop())){
                    System.out.println("\nErro: ("+ mexp.getLine() + ", " + mexp.getColumn() + ") Operacao de multiplicação e divisao so aceitam operandos de mesmo tipo!!!\n");
                    System.exit(0);
                }
            }
        }
        types.add(current);
    }

    public void visit(PexpFunction pexpfunction) {
        ArrayList<Stype> params = new ArrayList<Stype>();
        for(Exp i : pexpfunction.getExps()){
            i.accept(this);
            params.add(0,types.pop());
        }
        StyFunc f = funcs.get( new Funcao(pexpfunction.getId().getID(), params) );
        if(f==null){
            System.out.println("\nErro: ("+ pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Chamada de funcao " + pexpfunction.getId().getID() + " que nao existe com os parâmetros passados!!!\n");
            System.exit(0);
        }else{
            if(f.getRet().size()==0){
                System.out.println("\nErro: ("+ pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Funcao chamada " + pexpfunction.getId().getID() + " nao possui retorno");
                System.exit(0);
            }
            pexpfunction.getExp().accept(this);
            if(!types.pop().match(StyInt.newStyInt())){
                System.out.println("\nErro: ("+ pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Posição de acesso ao retorno da funcao deve ser do tipo inteiro!!!\n");
                System.exit(0);
            }else{
                try{
                    int pos = Integer.parseInt(pexpfunction.getExp().toString());
                    if(pos>=0 && pos<f.getRet().size()){
                        types.add(f.getRet().get(pos));
                    }else{
                        System.out.println("\nErro: ("+ pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Posição de acesso ao retorno da funcao inválida!!!\n");
                        System.exit(0);
                    }
                }catch(Exception e){
                    System.out.println("\nErro: ("+ pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Posição de acesso ao retorno da funcao deve ser um literal!!!\n");
                    System.exit(0);
                }
            }
        }
    }

    public void visit(PexpLvalue pexplvalue) {
        pexplvalue.getLvalue().accept(this);
    }

    public void visit(PexpNew pexpnew) {
        pexpnew.getType().accept(this);
        Stype current = types.pop();
        if(pexpnew.getExp()!=null){
            pexpnew.getExp().accept(this);
            if(!types.pop().match(StyInt.newStyInt())){
                System.out.println("\nErro: ("+ pexpnew.getLine() + ", " + pexpnew.getColumn() + ") Tamanho do array criado deve ser do tipo Int\n");
                System.exit(0);
            }
            current = new StyArr(current);
        }else if(pexpnew.getType().getNumColchetes()>0){
            System.out.println("\nErro: ("+ pexpnew.getLine() + ", " + pexpnew.getColumn() + ") Falta dimensão do array no new\n");
            System.exit(0);
        }
        if(current.match(StyInt.newStyInt()) || current.match(StyFloat.newStyFloat()) || current.match(StyBool.newStyBool()) || current.match(StyNull.newStyNull()) || current.match(StyChar.newStyChar())){
            System.out.println("\nErro: ("+ pexpnew.getLine() + ", " + pexpnew.getColumn() + ") Não é possível fazer new de tipos primitivos (Int, Float, Char ou Bool)\n");
            System.exit(0);
        } 
        types.add(current);
    }

    public void visit(PexpParenteses pexpparenteses) {
        pexpparenteses.getExp().accept(this);
    }

    public void visit(Print print) {
        print.getExp().accept(this);
        if(types.peek().equals(StyInt.newStyInt()) || types.peek().equals(StyFloat.newStyFloat()) || types.peek().equals(StyBool.newStyBool()) || types.peek().equals(StyChar.newStyChar())){
            //ok
        }else{
            System.out.println("\nErro: ("+ print.getLine() + ", " + print.getColumn() + ") Apenas prints de tipos primitivos (Int, Float, Char ou Bool) sao permitidos\n");
            System.exit(0);
        }
        types.pop();
    }

    public void visit(Read read) {
        addVar( (LvalueUnic)read.getLvalue(), StyInt.newStyInt(), read.getLine(), read.getColumn());  
    }

    public void visit(Return ret) {
        if(ret.getExps().size()==this.retornos.size()){
            for(int i=0;i<ret.getExps().size();i++){
                ret.getExps().get(i).accept(this);
                if(!this.retornos.get(i).match(types.pop())){
                    System.out.println("\nErro: ("+ ret.getLine() + ", " + ret.getColumn() + ") Tipo de retorno não bate com variável atribuida\n");
                    System.exit(0);
                }
            }
        }else{
            System.out.println("\nErro: ("+ ret.getLine() + ", " + ret.getColumn() + ") Esperado numero diferente de retornos\n");
            System.exit(0);
        }
    }

    public void visit(Rexp rexp) {
        rexp.getAexpLess1().accept(this);
        Stype current = types.pop();
        if(rexp.getAexpLess2()!=null){
            rexp.getAexpLess2().accept(this);
            if(current.match(types.pop())){
                if((current.match(StyInt.newStyInt())) || (current.match(StyFloat.newStyFloat()))){
                    current = StyBool.newStyBool();
                }else{
                    System.out.println("\nErro: ("+ rexp.getLine() + ", " + rexp.getColumn() + ") Operação de < só pode ser aplicada entre operandos do tipo Int ou Float\n");    
                    System.exit(0);
                }
            }else{
                System.out.println("\nErro: ("+ rexp.getLine() + ", " + rexp.getColumn() + ") Operação de < só pode ser aplicada entre operandos de mesmo tipo\n");
                System.exit(0);
            }
        }
        for(Aexp a : rexp.getAexp()){
            a.accept(this);
            if(current.match(types.pop())){
                current = StyBool.newStyBool();
            }else{
                System.out.println("\nErro: ("+ rexp.getLine() + ", " + rexp.getColumn() + ") Operação de comparação (== ou !=) só pode ser aplicada entre operandos de mesmo tipo\n");
                System.exit(0);
            }
        }
        types.add(current);
    }

    public void visit(Sexp sexp) {
        sexp.getSexpValue().accept(this);
        Stype current = types.peek();
        if(sexp.getOperator() == null){
            //nada a fazer
        }else if(sexp.getOperator()=="!"){
            if(!(current.match(StyBool.newStyBool()))){
                System.out.println("\nErro: ("+ sexp.getLine() + ", " + sexp.getColumn() + ") Operação de not (!) só pode ser aplicada em operando do tipo Bool\n");
                System.exit(0);
            }
        }else if(sexp.getOperator()=="-"){
            if(!((current.match(StyInt.newStyInt())) || (current.match(StyFloat.newStyFloat())))){
                System.out.println("\nErro: ("+ sexp.getLine() + ", " + sexp.getColumn() + ") Operação de menos unário (-) só pode ser aplicada em operando do tipo Int ou Float\n");
                System.exit(0);
            }
        }
    }

    public void visit(SexpValueBool sexpvaluebool) {
        types.add(StyBool.newStyBool());
    }

    public void visit(SexpValueChar sexpvaluechar) {
        types.add(StyChar.newStyChar());
    }

    public void visit(SexpValueFloat sexpvaluefloat) {
        types.add(StyFloat.newStyFloat());
    }

    public void visit(SexpValueInt sexpvalueint) {
        types.add(StyInt.newStyInt());
    }

    public void visit(SexpValueNull sexpvaluenull) {
        types.add(StyNull.newStyNull());
    }

    public void visit(SexpValuePexp sexpvaluepexp) {
        sexpvaluepexp.getValue().accept(this);
        if(this.l!=null){
            if(vars.peek().containsKey(l.getId().getID())){
                Stype s = vars.peek().get(l.getId().getID());
                for(Lvalue l : this.l.getLvalues()){
                    if(l instanceof LvalueArray){
                        ((LvalueArray)l).getExp().accept(this);
                        if(!types.pop().match(StyInt.newStyInt())){
                            System.out.println("\nErro: " + "(" + sexpvaluepexp.getLine() + ", " + sexpvaluepexp.getColumn() +  ") Acesso a posição de vetor so aceita tipo inteiro ");
                            System.exit(0);
                        }
                        if(s instanceof StyArr){
                            s=((StyArr)s).getArg();
                        }else{
                            System.out.println("\nErro: " + "(" + sexpvaluepexp.getLine() + ", " + sexpvaluepexp.getColumn() +  ") Acesso inválido a variável " + this.l.getId().getID());
                            System.exit(0);
                        }
                    }else if(l instanceof LvalueSelect){
                        if(s instanceof StyData){
                            if(((StyData)s).getCampos().containsKey(((LvalueSelect)l).getID().getID())){
                                s=((StyData)s).getCampos().get(((LvalueSelect)l).getID().getID());
                            }else{
                                System.out.println("\nErro: " + "(" + l.getLine() + ", " + l.getColumn() +  ") Acesso inválido a variável " + this.l.getId().getID());
                                System.exit(0);
                            }
                        }else{
                            System.out.println("\nErro: " + "(" + l.getLine() + ", " + l.getColumn() +  ") Acesso inválido a variável " + this.l.getId().getID());
                            System.exit(0);
                        }    
                    }
                }
                types.add(s);
            }else{
                System.out.println("\nErro: ("+ sexpvaluepexp.getLine() + ", " + sexpvaluepexp.getColumn() + ") Acesso a variável " + l.getId().getID() + " não declarada!!!");
                System.exit(0);
            }
        }
        l=null;
    }

    public void visit(Type type) {
        Stype s=null;
        if(type.getType()!=null){
            String tipo = type.getType();
            if(tipo.equals("Bool")){
                s=StyBool.newStyBool();
            }else if(tipo.equals("Char")){
                s=StyChar.newStyChar();
            }else if(tipo.equals("Int")){
                s=StyInt.newStyInt();
            }else if(tipo.equals("Float")){
                s=StyFloat.newStyFloat();
            }
            int j = type.getNumColchetes();
            //se for vetor
            while(j>0){
                s=new StyArr(s);
                j=j-1;
            }
        }else{
            //caso seja um tipo data
            if(dates.containsKey(type.getIDType().getID())){
                s = dates.get(type.getIDType().getID());
                int j = type.getNumColchetes();
                //se for vetor
                while(j>0){
                    s=new StyArr(s);
                    j=j-1;
                }
            }else{
                System.out.println("\nErro: " + "(" + type.getLine() + ", " + type.getColumn() +  ") Tipo "+ type.getIDType().getID() + " invalido");
                System.exit(0);
            }
        }
        this.types.add(s);
    }
}
