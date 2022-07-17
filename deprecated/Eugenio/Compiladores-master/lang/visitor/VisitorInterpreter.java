/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor;

import lang.ast.*;
import lang.visitor.types.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class VisitorInterpreter extends Visitor {

    private Stack<HashMap<String, Object>> vars;
    private HashMap<String, Func> funcs;
    private HashMap<String, Data> dates;
    private Stack<Object> operands;
    private Boolean retornou;
    private VisitorStatic v;

    private void addVar(LvalueUnic key, Object value, int line, int column){
        try{
            String id = key.getId().getID();
            if(key.getLvalues().isEmpty()){
                vars.peek().put(id, value);
            }else{
                Object o = vars.peek().get(id);
                for(Lvalue l : key.getLvalues()){ 
                    if(l instanceof LvalueArray){
                        ((LvalueArray)l).getExp().accept(this);
                        int pos = (int)Float.parseFloat(operands.pop().toString());
                        if(!l.equals(key.getLvalues().get(key.getLvalues().size()-1))){
                            o = ((ArrayList<Object>)o).get(pos);
                        }else{
                            ((ArrayList<Object>)o).set(pos, value);
                        }
                    }else if(l instanceof LvalueSelect){
                        String i = ((LvalueSelect) l ).getID().getID();
                        if(!l.equals(key.getLvalues().get(key.getLvalues().size()-1))){
                            if(((HashMap<String, Object>)o).containsKey(i)){
                                o = ((HashMap<String, Object>)o).get(i); 
                            }else{
                                System.out.println("\nErro: " + "(" + line + ", " + column +  ") Variavel nao definida" + "\n");
                                System.exit(0);
                            }
                        }else{
                            if(((HashMap<String, Object>)o).containsKey(i)){
                                ((HashMap<String, Object>)o).put(i,value);
                            }else{
                                System.out.println("\nErro: " + "(" + line + ", " + column +  ") Variavel nao definida" + "\n");
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println("\nErro: (" + line + ", " + column + ") " + "Null Pointer Exception");
            System.exit(0);
        }
    }

    public VisitorInterpreter(VisitorStatic v) {
        vars = new Stack<HashMap<String, Object>>();
        vars.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Func>();
        dates = new HashMap<String, Data>();
        operands = new Stack<Object>();
        this.v = v;
    }

    public void visit(Prog p) {
        Func main = null;
        for(Data d : p.getDatas()){
            dates.put(d.getId().getID(), d);
        }
        for (Func f : p.getFuncs()) {
            funcs.put(f.getID().getID(), f);
            if (f.getID().getID().equals("main")) {
                main = f;
            }
        }
        if (main == null) {
            System.out.println("\nErro: " + "O programa não possui uma main!" + "\n");
            System.exit(0);
        }else if(main.getTypeParams().size()>0){
            System.out.println("\nErro: " + "Main está recebendo parâmetros" + "\n");
            System.exit(0);
        }
        main.accept(this);
    }

    public void visit(Func func) {
        try{
            retornou=false;
            HashMap<String, Object> localVars = new HashMap<String, Object>();
            ArrayList<Stype> v = new ArrayList<Stype>();
            for(Type type: func.getTypeParams()){
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
                    HashMap<String,StyData> dates = this.v.getDates();
                    s = dates.get(type.getIDType().getID());
                    int j = type.getNumColchetes();
                    //se for vetor
                    while(j>0){
                        s=new StyArr(s);
                        j=j-1;
                    }
                }
                v.add(s);
            }
            StyFunc funca = this.v.getFuncs().get(new Funcao(func.getID().getID(), v));
            for(String var : funca.getVars().keySet()){
                Stype aux = funca.getVars().get(var);
                if(aux.match(StyInt.newStyInt())){
                    localVars.put(var, 0);
                }else if(aux.match(StyFloat.newStyFloat())){
                    localVars.put(var, 0.0);
                }else if(aux.match(StyBool.newStyBool())){
                    localVars.put(var, false);
                }else if(aux.match(StyChar.newStyChar())){
                    localVars.put(var, ' ');
                }else{
                    localVars.put(var, null);
                }
            }
            for (int i = 0; i < func.getIdsParams().size(); i++) {
                localVars.put(func.getIdsParams().get(i).getID(), operands.pop());
            }
            vars.push(localVars);
            for(Cmd c:func.getCmds()){
                c.accept(this);
                if(c instanceof Return){
                    Return r = (Return)c;
                    if(r.getExps().size()!=func.getTypeReturns().size()){
                        System.out.println("\nErro: " + "(" + r.getLine() + ", " + r.getColumn() + ") Retorno da função " + func.getID().getID() + " possui numero incorreto de expressoes\n" );
                        System.exit(0);
                    }
                    break;
                }
                if(retornou.equals(true)){
                    retornou=false;
                    break;
                }
            }
            vars.pop();
        }catch(Exception x){
            System.out.println("(" + func.getLine() + ", " + func.getColumn() + ") " + x.getMessage());
            System.exit(0);
        }
    }

    public void visit(Aexp aexp) {
        try {
            for(Mexp i : aexp.getMexps()) {
        		i.accept(this);
            }
            if(aexp.getSignals().size()>0) {
                float result=  Float.parseFloat(this.operands.pop().toString());
                for(int i=aexp.getSignals().size()-1;i>=0;i--) {
        			if(aexp.getSignals().get(i).equals("+")) {
        				result = Float.parseFloat(operands.pop().toString())+result;
        			}else if(aexp.getSignals().get(i).equals("-")) {
        				result = Float.parseFloat(operands.pop().toString())-result;
        			}
                }
                this.operands.add(result);
            }
        }catch(Exception x) {
            System.out.println("\nErro: " + "(" + aexp.getLine() + ", " + aexp.getColumn() + ") " + x.getMessage() + "/n");
            System.exit(0);
        }
    }

    public void visit(Attr attr) {
        try {
            attr.getExp().accept(this);
            addVar(attr.getLvalue(), operands.pop(), attr.getLine(), attr.getColumn());
        } catch (Exception x) {
            System.out.println("\nErro: " + "(" + attr.getLine() + ", " + attr.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }

    public void visit(Data data) {
    }

    public void visit(Decl decl) {
    }

    public void visit(Exp exp) {
        try {
            for (Rexp i : exp.getRexps()) {
        		i.accept(this);
            }
            if(exp.getRexps().size()>1) {
        		Boolean result=true;
        		for(int i=0;i<exp.getRexps().size();i++) {
        			result = result && (Boolean)this.operands.pop();
        		}
        		this.operands.add(result);
        	}
        }catch(Exception x) {
            System.out.println("\nErro: " + "(" + exp.getLine() + ", " + exp.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }

    public void visit(FunctionCall functioncall) {
        try{
            Func f = funcs.get(functioncall.getId().getID());
            if(f!=null){
                //verifica se o numero de parametros é correto
                if(functioncall.getExps().size()!=f.getIdsParams().size()){
                    System.out.println("\nErro: " + "(" + functioncall.getLine() + ", " + functioncall.getColumn() + ") Função " + functioncall.getId().getID() + " chamada com o numero de parametros incorreto" + "\n" );
                    System.exit(0);
                }
                if(functioncall.getLvalues().size()!=f.getTypeReturns().size()){
                    System.out.println("\nErro: " + "(" + functioncall.getLine() + ", " + functioncall.getColumn() + ") Função " + functioncall.getId().getID() + " chamada com numero de retornos incorreto" + "\n");
                    System.exit(0);
                }
                //empilhando os parametros
            	for(int i=functioncall.getExps().size()-1;i>=0;i--) {
            		functioncall.getExps().get(i).accept(this);
            	}
            	//chamando função
                f.accept(this);
                //setando os retornos
                for(int i=functioncall.getLvalues().size()-1;i>=0;i--){
                    functioncall.getLvalues().get(i).accept(this);
                    addVar((LvalueUnic)operands.pop(), operands.pop(), functioncall.getLine(),functioncall.getColumn());
                }
            }else{
                System.out.println("\nErro: " + "(" + functioncall.getLine() + ", " + functioncall.getColumn() + ") Função " + functioncall.getId().getID() + " não definida\n" );
                System.exit(0);
            }
        }catch(Exception x) {
            System.out.println("\nErro: " + "(" + functioncall.getLine() + ", " + functioncall.getColumn() + ") " + x.getMessage()+ "\n");
            System.exit(0);
        }
    }

    public void visit(ID id) {
        //nunca ira ser chamado
    	operands.add(id.getID());
    }

    public void visit(If i) {
        try {
            i.getCond().accept(this);
            if (operands.pop().toString().equals("true")) {
                for (Cmd c : i.getThen()) {
                    c.accept(this);
                    if(c instanceof Return){
                        retornou=true;
                        break;
                    }
                }
            } else {
                if (i.getElse() != null) {
                    for (Cmd c : i.getElse()) {
                        c.accept(this);
                        if(c instanceof Return){
                            retornou=true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception x) {
            System.out.println("\nErro: " + "(" + i.getLine() + ", " + i.getColumn() + ") " + x.getMessage()+ "\n");
            System.exit(0);
        }
    }
    
    public void visit(Iterate iterate) {
        try {
            iterate.getCond().accept(this);
            int k = (int)Float.parseFloat(operands.pop().toString());
            ArrayList<Cmd> loop = iterate.getLoop();
            for(int i=0;i<k;i++){
                for (Cmd c:loop) {
                    c.accept(this);
                    if(c instanceof Return){
                        retornou=true;
                        break;
                    }
                }
            }
        } catch (Exception x) {
            System.out.println("\nErro: " + "(" + iterate.getLine() + ", " + iterate.getColumn() + ") " + x.getMessage()+ "\n");
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
        this.operands.add(lvalueunic);
    }

    public void visit(Mexp mexp) {
        try {
        	for(SexpValue i : mexp.getSexps()) {
                i.accept(this);
        	}
        	if(mexp.getSignals().size()>0) {
                boolean inteiro = false;
                Object o = this.operands.pop();
                if(o instanceof Integer){
                    inteiro=true;
                }

                float result=Float.parseFloat(o.toString());
        		for(int i=mexp.getSignals().size()-1;i>=0;i--) {
        			if(mexp.getSignals().get(i).equals("*")) {
        				result = Float.parseFloat(this.operands.pop().toString())*result;
        			}else if(mexp.getSignals().get(i).equals("/")) {
                        if(inteiro){
                            result = Integer.parseInt(this.operands.pop().toString())/(int)result;
                        }else{
                            result = Float.parseFloat(this.operands.pop().toString())/result;
                        }
        			}else if(mexp.getSignals().get(i).equals("%")) {
        				result = Float.parseFloat(this.operands.pop().toString())%result;
        			}
        		}
        		this.operands.add(result);
        	}
        }catch(Exception e) {
        	System.out.println("\nErro: " + "(" + mexp.getLine() + ", " + mexp.getColumn() + ") " + e.getMessage()+ "\n");
            System.exit(0);
        }
    	
    }

    public void visit(PexpFunction pexpfunction) {
    	try {
    		Func f = this.funcs.get(pexpfunction.getId().getID());
            if(f!=null) {
                //verifica se numero de parametros passados é igual ao esperado
                if(pexpfunction.getExps().size()!=f.getIdsParams().size()){
                    System.out.println("\nErro: " + "(" + pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Função " + pexpfunction.getId().getID() + " chamada com o numero de parametros incorreto\n" );
                    System.exit(0);
                }
                //verifica se a posicao do retorno é valida
                pexpfunction.getExp().accept(this);
        		int pos = (int)Float.parseFloat(this.operands.pop().toString());
                if(pos<0 || pos>f.getTypeReturns().size()-1){
                    System.out.println("\nErro: " + "(" + pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Função " + pexpfunction.getId().getID() + " chamada com retorno errado\n" );                    
                    System.exit(0);
                }
                //empilha parametros
    			for(Exp i : pexpfunction.getExps()) {
                    i.accept(this);
        		}
    			//chama funcao
                f.accept(this);
    			//pega qual parametro retornar
    			Object retorno=null;
                for(int i=f.getTypeReturns().size()-1;i>=0;i--) {
                    if(i==pos) {
                        retorno=this.operands.pop();
        			}else {
        				this.operands.pop();
        			}
        		}
        		this.operands.add(retorno);
    		}else{
    			System.out.println("\nErro: " + "(" + pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") Função " + pexpfunction.getId().getID() + " não definida" );
                System.exit(0);
            }
    	}catch(Exception x) {
    		System.out.println("\nErro: " + "(" + pexpfunction.getLine() + ", " + pexpfunction.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }
    
    public void visit(PexpLvalue pexplvalue) {
    	pexplvalue.getLvalue().accept(this);
    }
    
    
    public void visit(PexpNew pexpnew) {
        try{
            Exp e = (Exp)pexpnew.getExp();
            if(e == null){
                if(dates.containsKey(pexpnew.getType().getIDType().getID())){
                    HashMap<String, Object> h = new HashMap<String, Object>();
                    Data d = dates.get(pexpnew.getType().getIDType().getID());
                    for(Decl dec : d.getDecl()){
                        h.put(dec.getId().getID(), null);
                    }
                    operands.add(h);
                }else{
                    operands.add(new Object());
                }
            }else{
                e.accept(this);
                int tam = (int)Float.parseFloat(operands.pop().toString()); 
                if(dates.containsKey(pexpnew.getType().getType())){
                    HashMap<String, Object> h = new HashMap<String, Object>();
                    Data d = dates.get(pexpnew.getType().getType());
                    for(Decl dec : d.getDecl()){
                        h.put(dec.getId().getID(), null);
                    }
                    ArrayList<HashMap<String,Object>> o = new ArrayList<HashMap<String,Object>>();
                    for(int i=0;i<tam;i++){
                        o.add(h);
                    }
                    operands.add(o);
                }else{
                    ArrayList<Object> o = new ArrayList<Object>();
                    for(int i=0;i<tam;i++){
                        o.add(null);
                    }
                    operands.add(o);
                }
            }
        }catch(Exception x) {
            System.out.println("\nErro: " + "(" + pexpnew.getLine() + ", " + pexpnew.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }
    
    public void visit(PexpParenteses pexpparenteses) {
    	pexpparenteses.getExp().accept(this);
    }
    
    public void visit(Print print) {
        try{
            print.getExp().accept(this);
            Object p = operands.pop();
            if(p instanceof Float){
                if(Float.parseFloat(p.toString()) == Math.rint(Float.parseFloat(p.toString()))){
                    System.out.print(Math.round(Float.parseFloat(p.toString())));
                }else{
                    System.out.print(p);        
                }
            }else{
                if(p==null){
                    System.out.println("null");
                }else if(p.toString().startsWith("'")){
                    String saida = p.toString().substring(1, p.toString().length()-1);
                    if (saida.equals("\\n")){
                        System.out.print("\n");
                    }else if (saida.equals("\\t")){
                        System.out.print("\t");
                    }else if (saida.equals("\\b")){
                        System.out.print("\b");
                    }else if (saida.equals("\\r")){
                        System.out.print("\r");
                    }else if (saida.equals("\\\\")){
                        System.out.print("\\");
                    }else if (saida.equals("\\'")){
                        System.out.print("'");
                    }else{
                        System.out.print(saida);
                    }
                }else{
                    System.out.print(p.toString());
                }
            }
        }catch (Exception x) {
            System.out.println("\nErro: " + "(" + print.getLine() + ", " + print.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }

    public void visit(Read read) {
        try {
            read.getLvalue().accept(this);
            Scanner scanner = new Scanner(System.in);
            int i = Integer.parseInt(scanner.next());
            addVar((LvalueUnic)this.operands.pop(), i, read.getLine(), read.getColumn());
        }catch(Exception x){
            System.out.println("\nErro: " + "(" + read.getLine() + ", " + read.getColumn() + ") " + "So entradas inteiras sao aceitas" + "\n");
            System.exit(0);
        }
    }

    public void visit(Return ret) {
    	try {
    		//empilha os valores contidos em ret
    		for (Exp i : ret.getExps()) {
    			i.accept(this);
    		}
    	}catch(Exception x) {
    		System.out.println("\nErro: " + "(" + ret.getLine() + ", " + ret.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }

    public void visit(Rexp rexp) {
    	try {
            rexp.getAexpLess1().accept(this);
            Object result=null;
    		if(rexp.getAexpLess2()!=null) {
    			rexp.getAexpLess2().accept(this);
    			result = Float.parseFloat(this.operands.pop().toString()) > Float.parseFloat(this.operands.pop().toString());
    		}else if(!rexp.getAexp().isEmpty()){
                result = this.operands.pop();
            }
    		for(int i=0; i<rexp.getAexp().size();i++) {
                rexp.getAexp().get(i).accept(this);
                if(result instanceof Integer){
                    result=(Integer)result*1.0;
                }
                Object o = this.operands.pop();
                if(o instanceof Integer){
                    o = (Integer)o*1.0;
                }
    			if(rexp.getSignal().get(i).equals("==")) {
                    result = result.toString().equals(o.toString());
    			}else if(rexp.getSignal().get(i).equals("!=")) {
    				result = !result.toString().equals(o.toString());
    			}
    		}
    		if(result!=null) {
    			this.operands.add(result);
    		}
    	}catch(Exception x){
            System.out.println("\nErro: " + "(" + rexp.getLine() + ", " + rexp.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }

    public void visit(Sexp sexp) {
    	try {
            sexp.getSexpValue().accept(this);
            String op=sexp.getOperator();
            if(op==null) {
                //nada a fazer 
    		}else if(op.equals("!")) {
                this.operands.add(! Boolean.parseBoolean(this.operands.pop().toString()));
    		}else if (op.equals("-")){
    			this.operands.add(- Float.parseFloat(this.operands.pop().toString()));
            }
    	}catch(Exception x){
            System.out.println("\nErro: " + "(" + sexp.getLine() + ", " + sexp.getColumn() + ") " + x.getMessage() + "\n");
            System.exit(0);
        }
    }

    public void visit(SexpValueBool sexpvaluebool) {
        operands.add(sexpvaluebool.getValue());
    }

    public void visit(SexpValueChar sexpvaluechar) {
        operands.add(new String("'"+sexpvaluechar.getValue()+"'"));
    }

    public void visit(SexpValueFloat sexpvaluefloat) {
        operands.add(sexpvaluefloat.getValue());
    }

    public void visit(SexpValueInt sexpvalueint) {
        operands.add(sexpvalueint.getValue());
    }

    public void visit(SexpValueNull sexpvaluenull) {
        operands.add(null);
    }

    public void visit(SexpValuePexp sexpvaluepexp) {
        sexpvaluepexp.getValue().accept(this);
        if(operands.peek() instanceof LvalueUnic){
            LvalueUnic l = (LvalueUnic)operands.pop();
            if(this.vars.peek().containsKey(l.getId().getID())){
                Object o = this.vars.peek().get(l.getId().getID());
                if(!l.getLvalues().isEmpty()){
                    for(Lvalue lv : l.getLvalues()){
                        if(lv instanceof LvalueArray){
                            ((LvalueArray)lv).getExp().accept(this);
                            o=((ArrayList<Object>)o).get((int)Float.parseFloat(this.operands.pop().toString()));
                        }else if (lv instanceof LvalueSelect){
                            if(((HashMap<String,Object>)o).containsKey(((LvalueSelect)lv).getID().getID())){
                                o = ((HashMap<String,Object>)o).get(((LvalueSelect)lv).getID().getID());
                            }else{
                                System.out.println("\nErro: " + "(" + sexpvaluepexp.getLine() + ", " + sexpvaluepexp.getColumn() + ") Acesso a variavel nao inicializada"); 
                                System.exit(0);
                            }
                        }
                    }

                }
                operands.add(o);
            }else{
                System.out.println("\nErro: " + "(" + sexpvaluepexp.getLine() + ", " + sexpvaluepexp.getColumn() + ") Acesso a variavel nao inicializada" + "\n");
                System.exit(0);
            }
        }
        
    }

    public void visit(Type type) {
    }
}
