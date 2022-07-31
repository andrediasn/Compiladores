/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.visitors;

import lang.ast.*;
import lang.tipos.*;

import java.util.ArrayList;
import java.util.Stack;

import java.util.HashMap;
public class TypeCheckVisitor extends Visitor
{
    private STyInt tyint = STyInt.newSTyInt();
    private STyFloat tyfloat = STyFloat.newSTyFloat();
    private STyBool tybool = STyBool.newSTyBool();
    private STyChar tychar = STyChar.newSTyChar();
    private STyNull tynull = STyNull.newSTyNull();
    private STyVar tyvar = STyVar.newSTyVar();
    private HashMap<String, STyData> datas; 
    private HashMap<String, SType> localEnv; 
    private HashMap<String, ArrayList<STyFunc>> funcs;
    private STyFunc tempFunc;
    private Stack<SType> stk;

    public TypeCheckVisitor()
    {
        funcs = new HashMap<String, ArrayList<STyFunc>>();
        tempFunc = null;
        datas = new HashMap<String, STyData>();
        localEnv = new HashMap<String, SType>();
        stk = new Stack<SType>();
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
                for(Decl de : d.getTypes())
                {
                    de.getType().accept(this); 
                    if(dat.elem.containsKey(de.getId())) 
                    {
                        throw new RuntimeException(de.getLine() + ", " + de.getColumn() + ": duplicate variable declaration!" );
                    }
                    dat.elem.put(de.getId(),stk.pop());
                }
                if(datas.containsKey(d.getId())) 
                {
                    throw new RuntimeException(d.getLine() + ", " + d.getColumn() + ": duplicate method declaration!" );
                }
                datas.put(d.getId(), dat);
            }
        }
        if(p.getFuncs()!=null)
        {
            for(Func f : p.getFuncs()){
                SType[] param = null;
                if (f.getID().equals("main")) {
                    mainFuncs = f;
                    if(mainFuncs.getParam() != null)
                    {
                        throw new RuntimeException(mainFuncs.getLine() + ", " + mainFuncs.getColumn() + ": main cannot have parameters!" );
                    }
                }
                if (f.getParam() != null)
                {
                    param = new SType[f.getParam().length];
                    for(int i = 0; i < f.getParam().length; i++ )
                    {
                        f.getParam()[i].getType().accept(this);
                        param[i] = stk.pop();
                    }
                }
                
                SType[] ret = null;
                if(f.getTypeReturn() != null)
                {
                    ret = new SType[f.getParam().length];
                    int i = 0;
                    for(Type t : f.getTypeReturn())
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
                        throw new RuntimeException(f.getLine() + ", " + f.getColumn() + ": 	duplicate method declaration!" );
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
                SType[] param = null;
                if (f.getParam() != null)
                {
                    param = new SType[f.getParam().length];
                    for(int i = 0; i < f.getParam().length; i++ ){
                        f.getParam()[i].getType().accept(this);
                        param[i] = stk.pop();
                    }
                }
                tempFunc = selecionaFunc(lista, param);
                f.accept(this);
            }
            if (mainFuncs == null) {
                throw new RuntimeException(p.getLine() + ", " + p.getColumn() + ": could not find the main class!" );
            }
        }
    }

    private boolean compara(SType[] aux, SType[] param)
    {
        boolean contem = false;
        if(aux.length == param.length)
        {
            contem = true;
            for(int j = 0; j < aux.length; j++)
            {
                SType a1 = aux[j];
                SType a2 = param[j];
                while(a1 instanceof STyArr && a2 instanceof STyArr)
                {
                    STyArr au1 = (STyArr)a1;
                    STyArr au2 = (STyArr)a2;
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

    private boolean comparaRetorno(SType[] aux, SType[] param)
    {
        boolean contem = true;
        for(int j = 0; j < aux.length; j++)
        {
            SType a1 = aux[j];
            SType a2 = param[j];
            while(a1 instanceof STyArr && a2 instanceof STyArr)
            {
                STyArr au1 = (STyArr)a1;
                STyArr au2 = (STyArr)a2;
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

    private boolean containsParamFunc(ArrayList<STyFunc> fun, SType[] param)
    {
        boolean contem = false;
        for(int i = 0; i < fun.size(); i++)
        {
            SType[] aux = fun.get(i).getParametro();
            if(param == null && aux == null)
            {
                return true;
            }
            if(aux.length == param.length)
            {
                contem = true;
                for(int j = 0; j < aux.length; j++)
                {
                    SType a1 = aux[j];
                    SType a2 = param[j];
                    while(a1 instanceof STyArr && a2 instanceof STyArr)
                    {
                        STyArr au1 = (STyArr)a1;
                        STyArr au2 = (STyArr)a2;
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

    private STyFunc selecionaFunc(ArrayList<STyFunc> fun, SType[] param)
    {
        boolean contem = false;
        STyFunc func = null; 
        for(int i = 0; i < fun.size(); i++)
        {
            SType[] aux = fun.get(i).getParametro();
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
                    SType a1 = aux[j];
                    SType a2 = param[j];
                    while(a1 instanceof STyArr && a2 instanceof STyArr)
                    {
                        STyArr au1 = (STyArr)a1;
                        STyArr au2 = (STyArr)a2;
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
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if( (tyr.match(tyint) && tyl.match(tyint)) ){
            stk.push(tyl);
        }else if(tyr.match(tyfloat) && tyl.match(tyfloat)){
            stk.push(tyr);
        }else{
            throw new RuntimeException( n.getLine() + ", " + n.getColumn() + ": operator " + opName +" cannot be applied " + tyl.toString() + " and " + tyr.toString() );
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
    public void visit(Mult e)
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
    public void visit(CModule e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if( tyr.match(tyint) && tyl.match(tyint)  )
        {
            stk.push(tyint);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": operator % cannot be applied " + tyl.toString() + " and " + tyr.toString() );
        }
    }

    @Override
    public void visit(And e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if( tyr.match(tybool) && tyl.match(tybool)  )
        {
            stk.push(tybool);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": operator & cannot be applied " + tyl.toString() + " and " + tyr.toString() );
        }
    }

    @Override
    public void visit(Lt e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
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
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": operator < cannot be applied " + tyl.toString() + " and " + tyr.toString() );
        }
    }

    @Override
    public void visit(Eq e)
    {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if(tyr.match(tyl))
        {
            stk.push(tybool);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Incompatible type for ==" );
        }
    }

    @Override
    public void visit(Not e)
    {
        e.getExpression().accept(this);
        SType tyr = stk.pop();
        if(tyr.match(tybool))
        {
            stk.push(tybool);
        }
        else
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": operator ! cannot be applied " + tyr.toString() );
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
    public void visit(Int e)
    {
        stk.push(tyint);
    }

    @Override
    public void visit(FloatV e)
    {
        stk.push(tyfloat);
    }

    @Override
    public void visit(CallCmd e)
    {
        if(funcs.containsKey(e.getName()))
        {
            ArrayList<STyFunc> lista = funcs.get(e.getName());
            Expr[] par = e.getExpressions();
            SType[] param = null;
            if(par != null)
            {
                param = new SType[par.length];
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
                if(e.getReturn()!=null)
                {
                    if(aux.getRetorno()!=null)
                    {
                        SType[] retor = new SType[e.getReturn().length];
                        int i = 0;
                        for(LValue l : e.getReturn())
                        {
                            l.accept(this);
                            retor[i] = stk.pop();
                            i+=1;
                        }
                        if(compara(retor, aux.getRetorno()))
                        {
                            i = 0;
                            for(LValue l : e.getReturn())
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
        e.getValue().accept(this);
        SType auxID = stk.pop();
        e.getExpression().accept(this);
        SType aux = stk.pop();

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
                    localEnv.put(e.getValue().getId(), aux);
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
        e.getExpression().accept(this);
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
        e.getExpression().accept(this);
        if(stk.pop().match(tyvar))
        {
            throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": Impressao de variavel nao declarada");
        }
    }

    private boolean verificaRetornoIf(If cmdIf)
    {
        boolean left = false;
        boolean right = false;
        StmtList aux = null;
        If auxIf = null;
        if(cmdIf.getThen() instanceof Return)
        {
            left = true;
        }
        else
        {
            if(cmdIf.getThen() instanceof StmtList)
            {
                aux = (StmtList)cmdIf.getThen();
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
            if(cmdIf.getElse() instanceof StmtList)
            {
                aux = (StmtList)cmdIf.getElse();
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
        StmtList aux = null;
        If auxIf = null;
        if(comandos != null)
        {
            for(Cmd c : comandos)
            {
                if(c instanceof Return)
                {
                    resultado = true;
                }
                if(c instanceof StmtList)
                {
                    aux = (StmtList) c;
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
        if(f.getParam()!=null)
        {
            int i = 0;
            for(Param p : f.getParam())
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
        if(f.getTypeReturn()!=null)
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
            if(e.getExpressions().length == tempFunc.getRetorno().length)
            {
                SType[] aux = new SType[e.getExpressions().length];
                int i = 0;
                for(Expr ex : e.getExpressions())
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
        if(datas.containsKey(t.getIdType()))
        {
            stk.push(datas.get(t.getIdType()));
        }
        else
        {
            throw new RuntimeException( t.getLine() + ", " + t.getColumn() + ": Data " + t.getIdType() + " inexistente");
        }
    }

    @Override
    public void visit(Array e) { 
        SType aux = stk.pop();
        e.getIndex().accept(this);
        if(stk.pop().match(tyint))
        {
            if (aux instanceof STyArr)
            {
                STyArr au = (STyArr)aux;
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
            Expr[] par = e.getExpressions();
            SType[] param = null;
            if(par != null)
            {
                param = new SType[par.length];
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
                e.getReturn().accept(this);
                if(stk.pop().match(tyint)) 
                {
                    if(aux.getRetorno()!=null)
                    {
                        if(e.getReturn() instanceof Int)
                        {
                            Int n = (Int)e.getReturn();
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
    public void visit(StmtList e) { 
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
        e.getExpression().accept(this);
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
    public void visit(Char e) { 
        stk.push(tychar);
    }

    @Override
    public void visit(LValue e) { 
        if(localEnv.containsKey(e.getId()))
        {
            stk.push(localEnv.get(e.getId()));
            if(!e.getAccess().isEmpty())
            {
                for(Access s : e.getAccess())
                {
                    s.accept(this);
                }
            }
        }
        else
        {
            if(e.getAccess().isEmpty())
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
    public void visit(SMinus e) { 
        e.getExpression().accept(this);
        SType tyr = stk.pop();
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
    public void visit(Neq e) { 
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
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
        BType t = e.getType().getBtype();
        t.accept(this);
        SType aux = stk.pop();
        STyArr au = null;
        int numColchetes = e.getType().getBraces();
        if(e.getExpression()!=null)
        {
            e.getExpression().accept(this);
            if(stk.pop().match(tyint))
            {
                numColchetes+=1;
                for(int i = 0; i < numColchetes; i++)
                {
                    au = new STyArr(aux);
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
                au = new STyArr(aux);
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
        e.getValue().accept(this);
        SType ty = stk.pop();
        if(!ty.match(tyint))
        {
            if(ty.match(tyvar))
            {
                localEnv.put(e.getValue().getId(), tyint);
            }
            else
            {
                throw new RuntimeException( e.getLine() + ", " + e.getColumn() + ": o comando read so aceita variavel do tipo inteiro" );
            }
        }
    }

    @Override
    public void visit(Type e) { 
        BType t = e.getBtype();
        t.accept(this);
        SType aux = stk.pop();
        STyArr au = null;
        int numColchetes = e.getBraces();
        for(int i = 0; i < numColchetes; i++)
        {
            au = new STyArr(aux);
            aux = au;
        }
        stk.push(aux);
    }

    @Override
    public void visit(AccessData e) { 
        SType aux = stk.pop();
        if (aux instanceof STyData)
        {
            STyData au = (STyData)aux;
            if(au.elem.containsKey(e.getIndex()))
            {
                aux = au.elem.get(e.getIndex());
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
