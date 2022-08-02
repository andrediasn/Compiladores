/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.visitors;

import lang.ast.*;
import lang.tipos.*;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Set;
import java.util.HashMap;
public class TypeVisitor extends Visitor
{
    private STyInt tyint = STyInt.newSTyInt();
    private STyFloat tyfloat = STyFloat.newSTyFloat();
    private STyBool tybool = STyBool.newSTyBool();
    private STyChar tychar = STyChar.newSTyChar();
    private STyNull tynull = STyNull.newSTyNull();
    private STyVar tyvar = STyVar.newSTyVar();
    private HashMap<String, STyData> datas; 
    private HashMap<String, STipo> localEnv; 
    private ArrayList<HashMap<String, STipo>> env;
    private HashMap<String, ArrayList<STyFunc>> funcs;
    private STyFunc tempFunc;
    private Stack<STipo> stk;
    private ArrayList<STyFunc> styfuncs;
    public TypeVisitor()
    {
        funcs = new HashMap<String, ArrayList<STyFunc>>();
        tempFunc = null;
        datas = new HashMap<String, STyData>();
        localEnv = new HashMap<String, STipo>();
        stk = new Stack<STipo>();
        env = new ArrayList<HashMap<String, STipo>>();
        styfuncs = new ArrayList<STyFunc>();
    }

    public ArrayList<HashMap<String, STipo>> getEnv()
    {
        return this.env;
    }

    public ArrayList<STyFunc> getfuncs()
    {
        return this.styfuncs;
    }

    @Override
    public void visit(Program p) 
    {
        Func mainFuncs = null;
        ArrayList<STyFunc> lista = null;
        if (p.getDatas() != null)
        {
            for(Data d : p.getDatas())
            {
                STyData dat = new STyData(d.getId());
                for(Decl de : d.getDecls())
                {
                    de.getTipos().accept(this); 
                    if(dat.elem.containsKey(de.getId())) // verificar repeticao
                    {
                        throw new RuntimeException(de.getLine() + ", " + de.getColumn() + ": nome de elemento de data repetido" );
                    }
                    dat.elem.put(de.getId(),stk.pop());
                }
                if(datas.containsKey(d.getId())) // verificar repeticao
                {
                    throw new RuntimeException(d.getLine() + ", " + d.getColumn() + ": nome de data repetido" );
                }
                datas.put(d.getId(), dat);
            }
        }
        if(p.getFuncs()!=null)
        {
            for(Func f : p.getFuncs()){
                STipo[] param = null;
                if (f.getID().equals("main")) {
                    mainFuncs = f;
                    if(mainFuncs.getParams() != null)
                    {
                        throw new RuntimeException(mainFuncs.getLine() + ", " + mainFuncs.getColumn() + ": Funcao main nao pode ter parametros" );
                    }
                }
                if (f.getParams() != null)
                {
                    param = new STipo[f.getParams().length];
                    for(int i = 0; i < f.getParams().length; i++ )
                    {
                        f.getParams()[i].getTipo().accept(this);
                        param[i] = stk.pop();
                    }
                }
                
                STipo[] ret = null;
                if(f.getTipo() != null)
                {
                    ret = new STipo[f.getTipo().length];
                    int i = 0;
                    for(Tipo t : f.getTipo())
                    {
                        t.accept(this);
                        ret[i] = stk.pop();
                        i += 1;
                    }
                }
                STyFunc aux = new STyFunc(f.getID(), param, ret);
                if(funcs.containsKey(f.getID()))
                {
                    lista = funcs.get(f.getID());
                    if(containsParamFunc(lista, param)) // testar se repetiu funcao
                    {
                        throw new RuntimeException(f.getLine() + ", " + f.getColumn() + ": Funcao repetida" );
                    }
                    else
                    {
                        lista.add(aux);
                        funcs.put(f.getID(), lista);
                    }
                }
                else
                {
                    lista = new ArrayList<STyFunc>();
                    lista.add(aux);
                    funcs.put(f.getID(), lista);
                }
            }
            for(Func f : p.getFuncs()){
                localEnv.clear();
                lista = funcs.get(f.getID());
                STipo[] param = null;
                if (f.getParams() != null)
                {
                    param = new STipo[f.getParams().length];
                    for(int i = 0; i < f.getParams().length; i++ ){
                        f.getParams()[i].getTipo().accept(this);
                        param[i] = stk.pop();
                    }
                }
                tempFunc = selecionaFunc(lista, param);
                f.accept(this);
                auxiliarEnv();
            }
            if (mainFuncs == null) {
                throw new RuntimeException(p.getLine() + ", " + p.getColumn() + ": Nao existe funcao main" );
            }
        }
    }
    private void auxiliarEnv()
    {
        HashMap<String, STipo> aux = new HashMap<String, STipo>();
        Set<String> keys = localEnv.keySet();
        for (String key : keys) {
            aux.put(key, localEnv.get(key));
        }
        env.add(aux);
    }
    private boolean compara(STipo[] aux, STipo[] param)
    {
        boolean contem = false;
        if(aux.length == param.length)
        {
            contem = true;
            for(int j = 0; j < aux.length; j++)
            {
                STipo a1 = aux[j];
                STipo a2 = param[j];
                while(a1 instanceof STyArray && a2 instanceof STyArray)
                {
                    STyArray au1 = (STyArray)a1;
                    STyArray au2 = (STyArray)a2;
                    a1 = au1.getArg();
                    a2 = au2.getArg();
                }
                if(!(a1.match(tyvar) || a2.match(tyvar)))
                {
                    if(!a1.match(a2))
                    {
                        contem = false;
                    }
                }        
            }
        }   
        return contem;
    }

    private boolean comparaRetorno(STipo[] aux, STipo[] param)
    {
        boolean contem = true;
        for(int j = 0; j < aux.length; j++)
        {
            STipo a1 = aux[j];
            STipo a2 = param[j];
            while(a1 instanceof STyArray && a2 instanceof STyArray)
            {
                STyArray au1 = (STyArray)a1;
                STyArray au2 = (STyArray)a2;
                a1 = au1.getArg();
                a2 = au2.getArg();
            }
            if(!a1.match(a2))
            {
                contem = false;
            }      
        } 
        return contem;
    }

    private boolean containsParamFunc(ArrayList<STyFunc> fun, STipo[] param)
    {
        boolean contem = false;
        for(int i = 0; i < fun.size(); i++)
        {
            STipo[] aux = fun.get(i).getParametro();
            if(param == null && aux == null)
            {
                return true;
            }
            if(aux.length == param.length)
            {
                contem = true;
                for(int j = 0; j < aux.length; j++)
                {
                    STipo a1 = aux[j];
                    STipo a2 = param[j];
                    while(a1 instanceof STyArray && a2 instanceof STyArray)
                    {
                        STyArray au1 = (STyArray)a1;
                        STyArray au2 = (STyArray)a2;
                        a1 = au1.getArg();
                        a2 = au2.getArg();
                    }
                    if(!a1.match(a2))
                    {
                        contem = false;
                    }
                }
            }
            if(contem)
            {
                return true;
            }
        }
        return false;
    }

    private STyFunc selecionaFunc(ArrayList<STyFunc> fun, STipo[] param)
    {
        boolean contem = false;
        STyFunc func = null; 
        for(int i = 0; i < fun.size(); i++)
        {
            STipo[] aux = fun.get(i).getParametro();
            if(param == null && aux == null)
            {
                func = fun.get(i);
                return func;
            }
            if(aux.length == param.length)
            {
                contem = true;
                for(int j = 0; j < aux.length; j++)
                {
                    STipo a1 = aux[j];
                    STipo a2 = param[j];
                    while(a1 instanceof STyArray && a2 instanceof STyArray)
                    {
                        STyArray au1 = (STyArray)a1;
                        STyArray au2 = (STyArray)a2;
                        a1 = au1.getArg();
                        a2 = au2.getArg();
                    }
                    if(!a1.match(a2))
                    {
                        contem = false;
                    }
                }
            }
            if(contem)
            {
                func = fun.get(i);
                return func;
            }
        }
        return func;
    }

    private void typeArithmeticBinOp(SuperNode n, String opName){
        STipo tyr = stk.pop();
        STipo tyl = stk.pop();
        if( (tyr.match(tyint) && tyl.match(tyint)) ){
            stk.push(tyl);
        }else if(tyr.match(tyfloat) && tyl.match(tyfloat)){
            stk.push(tyr);
        }else{
            throw new RuntimeException( n.getLine() + ", " + n.getColumn() + ": Operador " + opName +" não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
        }
    }

    @Override
    public void visit(Add e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"+");
    }

    @Override
    public void visit(Sub e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"-");
    }

    @Override
    public void visit(Mul e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"*");
    }

    @Override
    public void visit(Div e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"/");
    }

    @Override
    public void visit(Mod e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        STipo tyr = stk.pop();
        STipo tyl = stk.pop();
        if( tyr.match(tyint) && tyl.match(tyint)  )
        {
            stk.push(tyint);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Operador % não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
        }
    }

    @Override
    public void visit(And e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        STipo tyr = stk.pop();
        STipo tyl = stk.pop();
        if( tyr.match(tybool) && tyl.match(tybool)  )
        {
            stk.push(tybool);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Operador & não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
        }
    }

    @Override
    public void visit(Lt e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        STipo tyr = stk.pop();
        STipo tyl = stk.pop();
        if(tyr.match(tyint) && tyl.match(tyint))
        {
            stk.push(tybool);
        }
        else
        {
            if(tyr.match(tyfloat) && tyl.match(tyfloat))
            {
                stk.push(tybool);
            }
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Operador < não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
        }
    }

    @Override
    public void visit(Eq e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        STipo tyr = stk.pop();
        STipo tyl = stk.pop();
        if(tyr.match(tyl))
        {
            stk.push(tybool);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Operador == necessita que as duas expressoes sejam do mesmo tipo" );
        }
    }

    @Override
    public void visit(Not e)
    {
        e.getExpr().accept(this);
        STipo tyr = stk.pop();
        if(tyr.match(tybool))
        {
            stk.push(tybool);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Operador ! não se aplica ao tipo " + tyr.toString() );
        }
    }

    @Override
    public void visit(True e)
    {
        stk.push(tybool);
    }

    @Override
    public void visit(False e)
    {
        stk.push(tybool);
    }

    @Override
    public void visit(NInt e)
    {
        stk.push(tyint);
    }

    @Override
    public void visit(NFloat e)
    {
        stk.push(tyfloat);
    }

    @Override
    public void visit(Call e)
    {
        if(funcs.containsKey(e.getName()))
        {
            ArrayList<STyFunc> lista = funcs.get(e.getName());
            Expr[] par = e.getArgs();
            STipo[] param = null;
            if(par != null)
            {
                param = new STipo[par.length];
                int i = 0;
                for(Expr p : par)
                {
                    p.accept(this);
                    param[i] = stk.pop();
                    i+=1;
                }
            }
            STyFunc aux = selecionaFunc(lista, param);
            if(aux != null)
            {
                if(e.getRet()!=null)
                {
                    if(aux.getRetorno()!=null)
                    {
                        STipo[] retor = new STipo[e.getRet().length];
                        int i = 0;
                        for(Lvalue l : e.getRet())
                        {
                            l.accept(this);
                            retor[i] = stk.pop();
                            i+=1;
                        }
                        if(compara(retor, aux.getRetorno()))
                        {
                            i = 0;
                            for(Lvalue l : e.getRet())
                            {
                                if(retor[i].match(tyvar))
                                {
                                    if(aux.getRetorno()[i] instanceof STyNull)
                                    {
                                        throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": retorno nulo para variavel nao declarada"  );
                                    }
                                    else
                                    {
                                        localEnv.put(l.getId(),aux.getRetorno()[i]);
                                    }
                                    
                                }
                                i+=1;
                            }
                        }
                        else
                        {
                            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": tipos de variaveis que vao receber o retorno estao errados ou numero de variaveis diferente do numero de retorno" );
                        }
                    }
                    else
                    {
                        throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Funcao nao tem retorno " );
                    }
                }
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Funcao com tipos de parametros errados " );
            }
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Funcao nao declarada " );
        }
    }

    @Override
    public void visit(Attr e)
    {
        e.getID().accept(this);
        STipo auxID = stk.pop();
        e.getExp().accept(this);
        STipo aux = stk.pop();

        if(!aux.match(tyvar))
        {
            if(auxID.match(tyvar))
            {
                if(aux instanceof STyNull)
                {
                    throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": expr nula para variavel nao declarada" );
                }
                else
                {
                    localEnv.put(e.getID().getId(), aux);
                }
                
            }
            else
            {
                if(!auxID.match(aux))
                {
                    throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Variavel nao e do tipo da expressao" );
                }
            }
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Variavel da expressao nao declarada" );
        }

    }

    @Override
    public void visit(If e)  
    {
        e.getTeste().accept(this);
        if(stk.pop().match(tybool))
        {
            e.getThen().accept(this);
            if (e.getElse() != null)
            {
                e.getElse().accept(this);
            }
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Expressão de teste do IF deve ter tipo Bool");
        }
    }

    @Override
    public void visit(Print e)
    {
        e.getExpr().accept(this);
        if(stk.pop().match(tyvar))
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Impressao de variavel nao declarada");
        }
    }

    private boolean verificaRetornoIf(If cmdIf)
    {
        boolean left = false;
        boolean right = false;
        CmdList aux = null;
        If auxIf = null;
        if(cmdIf.getThen() instanceof Return)
        {
            left = true;
        }
        else
        {
            if(cmdIf.getThen() instanceof CmdList)
            {
                aux = (CmdList)cmdIf.getThen();
                left = verificaRetorno(aux.getList());
            }
            else
            {
                if(cmdIf.getThen() instanceof If)
                {
                    auxIf = (If) cmdIf.getThen();
                    if(auxIf.getElse() != null)
                    {
                        left = verificaRetornoIf(auxIf);
                    }
                }
            }
        }
        if(cmdIf.getElse() instanceof Return)
        {
            right = true;
        }
        else
        {
            if(cmdIf.getElse() instanceof CmdList)
            {
                aux = (CmdList)cmdIf.getElse();
                right = verificaRetorno(aux.getList());
            }
            else
            {
                if(cmdIf.getElse() instanceof If)
                {
                    auxIf = (If) cmdIf.getElse();
                    if(auxIf.getElse() != null)
                    {
                        right = verificaRetornoIf(auxIf);
                    }
                }
            }
        }
        return left && right;
    }

    private boolean verificaRetorno(Cmd[] comandos)
    {
        boolean resultado = false;
        CmdList aux = null;
        If auxIf = null;
        if(comandos != null)
        {
            for(Cmd c : comandos)
            {
                if(c instanceof Return)
                {
                    resultado = true;
                }
                if(c instanceof CmdList)
                {
                    aux = (CmdList) c;
                    resultado = verificaRetorno(aux.getList());
                }
            }

            if(!resultado)
            {
                for(Cmd c : comandos)
                {
                    if(c instanceof If)
                    {
                        auxIf = (If) c;
                        if(auxIf.getElse() != null)
                        {
                            resultado = verificaRetornoIf(auxIf);
                        }
                    }

                }
            }
        }
        return resultado;
    }

    @Override
    public void visit(Func f)
    { 
        if(f.getParams()!=null)
        {
            int i = 0;
            for(Param p : f.getParams())
            {
                if(localEnv.containsKey(p.getID()))
                {
                    throw new RuntimeException( f.getLine() + ", " + f.getColumn() + ": Parametros com identificadores iguais");
                }
                else
                {
                    localEnv.put(p.getID(), tempFunc.getParametro()[i]);
                }
                i+=1;
            }
        }
        if(f.getTipo()!=null)
        {
            if(!verificaRetorno(f.getBody()))
            {
                throw new RuntimeException( f.getLine() + ", " + f.getColumn() + ": Funcao nao retorna o que foi declarado como retorno");
            }
        }
        if(f.getBody()!=null)
        {
            for(Cmd c : f.getBody())
            {
                c.accept(this);
            }
        }
    }

    @Override
    public void visit(Data d) { 

    }

    @Override
    public void visit(Decl e) { 

    }

    @Override
    public void visit(Return e) 
    {
        if(tempFunc.getRetorno() != null)
        {
            if(e.getExpr().length == tempFunc.getRetorno().length)
            {
                STipo[] aux = new STipo[e.getExpr().length];
                int i = 0;
                for(Expr ex : e.getExpr())
                {
                    ex.accept(this);
                    aux[i] = stk.pop();
                    i+=1;
                }
                if(!comparaRetorno(aux, tempFunc.getRetorno()))
                {
                    throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": retorno com tipos diferentes do proposto pela funcao");
                }
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": retorno com numero diferente dos retornos declarados pela funcao");
            }
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Funcao nao pode ter retorno");
        }
    }

    @Override
    public void visit(Param e) {} 

    @Override
    public void visit(TyInt t) 
    {
        stk.push(tyint);
    }

    @Override
    public void visit(TyFloat t) 
    {
        stk.push(tyfloat);
    }

    @Override
    public void visit(TyBool t) 
    {
        stk.push(tybool);
    }

    @Override
    public void visit(TyChar t) { 
        stk.push(tychar);
    }

    @Override
    public void visit(TyID t) { 
        if(datas.containsKey(t.getIdTipo()))
        {
            stk.push(datas.get(t.getIdTipo()));
        }
        else
        {
            throw new RuntimeException( t.getLine() + ", " + t.getColumn() + ": Data " + t.getIdTipo() + " inexistente");
        }
    }

    @Override
    public void visit(ArraySeletor e) { 
        STipo aux = stk.pop();
        e.getIndice().accept(this);
        if(stk.pop().match(tyint))
        {
            if (aux instanceof STyArray)
            {
                STyArray au = (STyArray)aux;
                aux = au.getArg();
                stk.push(aux);
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": acesso errado a array" );
            }
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Expressão de acesso a array deve ter tipo int");
        }
    }

    @Override
    public void visit(CallExpr e) { 
        if(funcs.containsKey(e.getName()))
        {
            ArrayList<STyFunc> lista = funcs.get(e.getName());
            Expr[] par = e.getArgs();
            STipo[] param = null;
            if(par != null)
            {
                param = new STipo[par.length];
                int i = 0;
                for(Expr p : par)
                {
                    p.accept(this);
                    param[i] = stk.pop();
                    i+=1;
                }
            }
            STyFunc aux = selecionaFunc(lista, param);
            if(aux != null)
            {
                styfuncs.add(aux);
                e.getRet().accept(this);
                if(stk.pop().match(tyint)) // getRet retorna expr, sempre tem
                {
                    if(aux.getRetorno()!=null)
                    {
                        if(e.getRet() instanceof NInt)
                        {
                            NInt n = (NInt)e.getRet();
                            int nu = n.getValue();
                            if(aux.getRetorno().length > nu && nu >= 0)
                            {
                                stk.push(aux.getRetorno()[nu]);
                            }
                            else
                            {
                                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Acesso a um retorno inexistente " );
                            }
                        }
                        else
                        {
                            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": So aceita numeros inteiros no acesso ao retorno " );
                        }
                    }
                    else
                    {
                        throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Funcao nao tem retorno " );
                    }
                }
                else
                {
                    throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Para selecionar Retorno precisa ser do tipo int " );
                }
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Funcao com tipos de parametro errados ou numero diferente de parametros" );
            }
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Funcao nao declarada " );
        }
    }

    @Override
    public void visit(CmdList e) { 
        if(e.getList() != null)
        {
            for(Cmd c : e.getList())
            {
                c.accept(this);
            }
        }
    }

    @Override
    public void visit(Iterate e)
    {
        e.getTeste().accept(this);
        if(stk.pop().match(tyint))
        {
            e.getBody().accept(this);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Expressão de teste do iterate deve ter tipo int");
        }
    }

    @Override
    public void visit(LChar e) { 
        stk.push(tychar);
    }

    @Override
    public void visit(Lvalue e) { 
        if(localEnv.containsKey(e.getId()))
        {
            stk.push(localEnv.get(e.getId()));
            if(!e.getSeletores().isEmpty())
            {
                for(Seletor s : e.getSeletores())
                {
                    s.accept(this);
                }
            }
        }
        else
        {
            if(e.getSeletores().isEmpty())
            {
                stk.push(tyvar);
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": variavel array ou data nao declarado");
            }
        }
    }

    @Override
    public void visit(Neg e) { 
        e.getExpr().accept(this);
        STipo tyr = stk.pop();
        if(tyr.match(tyfloat))
        {
            stk.push(tyfloat);
        }
        else
        {
            if(tyr.match(tyint))
            {
                stk.push(tyint);
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Operador ! não se aplica ao tipo " + tyr.toString() );
            }
        }
    }

    @Override
    public void visit(NEq e) { 
        e.getLeft().accept(this);
        e.getRight().accept(this);
        STipo tyr = stk.pop();
        STipo tyl = stk.pop();
        if( tyr.match(tyl) )
        {
            stk.push(tybool);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Operador != necessita que as duas expressoes sejam do mesmo tipo" );
        }
    }

    @Override
    public void visit(New e) { 
        Btype t = e.getTipo().getBtype();
        t.accept(this);
        STipo aux = stk.pop();
        STyArray au = null;
        int numColchetes = e.getTipo().getColchetes();
        if(e.getExp()!=null)
        {
            e.getExp().accept(this);
            if(stk.pop().match(tyint))
            {
                numColchetes+=1;
                for(int i = 0; i < numColchetes; i++)
                {
                    au = new STyArray(aux);
                    aux = au;
                }
                stk.push(aux);
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Tamanho de vetor deve ser do tipo int" );
            }
        }
        else
        {
            for(int i = 0; i < numColchetes; i++)
            {
                au = new STyArray(aux);
                aux = au;
            }
            stk.push(aux);
        }
    }

    @Override
    public void visit(Null e) { 
        stk.push(tynull);
    }

    @Override
    public void visit(Read e) { 
        e.getVar().accept(this);
        STipo ty = stk.pop();
        if(!ty.match(tyint))
        {
            if(ty.match(tyvar))
            {
                localEnv.put(e.getVar().getId(), tyint);
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": o comando read so aceita variavel do tipo inteiro" );
            }
        }
    }

    @Override
    public void visit(Tipo e) { 
        Btype t = e.getBtype();
        t.accept(this);
        STipo aux = stk.pop();
        STyArray au = null;
        int numColchetes = e.getColchetes();
        for(int i = 0; i < numColchetes; i++)
        {
            au = new STyArray(aux);
            aux = au;
        }
        stk.push(aux);
    }

    @Override
    public void visit(DataSeletor e) { 
        STipo aux = stk.pop();
        if (aux instanceof STyData)
        {
            STyData au = (STyData)aux;
            if(au.elem.containsKey(e.getId()))
            {
                aux = au.elem.get(e.getId());
                stk.push(aux);
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": acesso errado a elemento de data" );
            }
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": acesso errado a data" );
        }
    }
}
