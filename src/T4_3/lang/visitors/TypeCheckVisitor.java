/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.visitors;

import lang.ast.*;
import lang.langUtil.*;

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
    private STyErr tyerr = STyErr.newSTyErr();

    private HashMap<String, STyData> datas; 
    private HashMap<String, SType> env; 
    private HashMap<String, ArrayList<STyFunc>> funcs;

    private STyFunc tempFunc;
    private Stack<SType> stk;

    private ArrayList<String> logError;

    public TypeCheckVisitor() {
        funcs = new HashMap<String, ArrayList<STyFunc>>();
        tempFunc = null;
        datas = new HashMap<String, STyData>();
        env = new HashMap<String, SType>();
        stk = new Stack<SType>();
        logError = new ArrayList<String>();
    }

    public int getNumErrors() { 
        return logError.size(); 
    }
     
    public void printErrors() { 
        for(String s : logError){
            System.out.println(s);
        }
    }

    private void error(int l, int c, String msg){
        logError.add(" (" + l + "," + c + "): " + msg);
        stk.push(tyerr);
    }

    @Override
    public void visit(Program p) {
        Func main = null;
        ArrayList<STyFunc> lista = null;
        if (p.getDatas() != null) {
            for(Data data : p.getDatas()) {
                STyData stydata = new STyData(data.getId());
                for(Decl de : data.getTypes()) {
                    de.getType().accept(this); 
                    if(stydata.elem.containsKey(de.getId())) {
                        error(de.getLine(), de.getColumn(), "duplicate variable declaration!");
                    }
                    stydata.elem.put(de.getId(),stk.pop());
                }
                if(datas.containsKey(data.getId())) {
                    error(data.getLine(), data.getColumn(), "duplicate method declaration!");
                }
                datas.put(data.getId(), stydata);
            }
        }
        if(p.getFuncs()!=null) {
            for(Func f : p.getFuncs()){
                SType[] param = null;
                if (f.getID().equals("main")) {
                    main = f;
                    if(main.getParam() != null) {
                        error(main.getLine(), main.getColumn(), "main cannot have parameters!" );
                    }
                }
                if (f.getParam() != null) {
                    param = new SType[f.getParam().length];
                    for(int i = 0; i < f.getParam().length; i++ ) {
                        f.getParam()[i].getType().accept(this);
                        param[i] = stk.pop();
                    }
                }
                SType[] ret = null;
                if(f.getType() != null) {
                    ret = new SType[f.getParam().length];
                    int i = 0;
                    for(Type t : f.getType()) {
                        if(i >= f.getParam().length) {
                            error(f.getLine(), f.getColumn(), "too many return types!" );
                            break;
                        }
                        t.accept(this);
                        ret[i] = stk.pop();
                        i += 1;
                    }
                }
                STyFunc aux = new STyFunc(f.getID(), param, ret);
                if(funcs.containsKey(f.getID())) {
                    lista = funcs.get(f.getID());
                    if(containsParamFunc(lista, param)) {// testar se repetiu funcao
                        error(f.getLine(), f.getColumn(), "	duplicate method declaration!" );
                    }
                    else {
                        lista.add(aux);
                        funcs.put(f.getID(), lista);
                    }
                }
                else {
                    lista = new ArrayList<STyFunc>();
                    lista.add(aux);
                    funcs.put(f.getID(), lista);
                }
            }
            for(Func f : p.getFuncs()) {
                env.clear();
                lista = funcs.get(f.getID());
                SType[] param = null;
                if (f.getParam() != null) {
                    param = new SType[f.getParam().length];
                    for(int i = 0; i < f.getParam().length; i++ ) {
                        f.getParam()[i].getType().accept(this);
                        param[i] = stk.pop();
                    }
                }
                tempFunc = selecionaFunc(lista, param);
                f.accept(this);
            }
            if (main == null) {
                error(p.getLine(), p.getColumn(), "could not find the main class!" );
            }
        }
    }

    private boolean check(SType[] aux, SType[] param) {
        boolean ck = false;
        if(aux.length == param.length) {
            ck = true;
            for(int j = 0; j < aux.length; j++) {
                SType a1 = aux[j];
                SType a2 = param[j];
                while(a1 instanceof STyArr && a2 instanceof STyArr) {
                    STyArr au1 = (STyArr)a1;
                    STyArr au2 = (STyArr)a2;
                    a1 = au1.getArg();
                    a2 = au2.getArg();
                }
                if(!(a1.match(tyvar) || a2.match(tyvar))) {
                    if(!a1.match(a2)) {
                        ck = false;
                    }
                }        
            }
        }   
        return ck;
    }

    private boolean checkReturn(SType[] aux, SType[] param) {
        boolean ck = true;
        for(int j = 0; j < aux.length; j++) {
            SType a1 = aux[j];
            SType a2 = param[j];
            while(a1 instanceof STyArr && a2 instanceof STyArr) {
                STyArr au1 = (STyArr)a1;
                STyArr au2 = (STyArr)a2;
                a1 = au1.getArg();
                a2 = au2.getArg();
            }
            if(!a1.match(a2)) {
                ck = false;
            }      
        } 
        return ck;
    }

    private boolean containsParamFunc(ArrayList<STyFunc> fun, SType[] param) {
        boolean ck = false;
        for(int i = 0; i < fun.size(); i++) {
            SType[] aux = fun.get(i).getParametro();
            if(param == null && aux == null) {
                return true;
            }
            if(aux.length == param.length) {
                ck = true;
                for(int j = 0; j < aux.length; j++) {
                    SType a1 = aux[j];
                    SType a2 = param[j];
                    while(a1 instanceof STyArr && a2 instanceof STyArr) {
                        STyArr au1 = (STyArr)a1;
                        STyArr au2 = (STyArr)a2;
                        a1 = au1.getArg();
                        a2 = au2.getArg();
                    }
                    if(!a1.match(a2)) {
                        ck = false;
                    }
                }
            }
            if(ck) {
                return true;
            }
        }
        return false;
    }

    private STyFunc selecionaFunc(ArrayList<STyFunc> fun, SType[] param) {
        boolean ck = false;
        STyFunc func = null; 
        for(int i = 0; i < fun.size(); i++) {
            SType[] aux = fun.get(i).getParametro();
            if(param == null && aux == null) {
                func = fun.get(i);
                return func;
            }
            if(param != null && aux != null && aux.length == param.length) {
                ck = true;
                for(int j = 0; j < aux.length; j++) {
                    SType a1 = aux[j];
                    SType a2 = param[j];
                    while(a1 instanceof STyArr && a2 instanceof STyArr) {
                        STyArr au1 = (STyArr)a1;
                        STyArr au2 = (STyArr)a2;
                        a1 = au1.getArg();
                        a2 = au2.getArg();
                    }
                    if(!a1.match(a2)) {
                        ck = false;
                    }
                }
            }
            if(ck) {
                func = fun.get(i);
                return func;
            }
        }
        return func;
    }

    private void typeArithmeticBinOp(SuperNode n, String opName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if( (tyr.match(tyint) && tyl.match(tyint)) ) {
            stk.push(tyl);
        } else if (tyr.match(tyfloat) && tyl.match(tyfloat)) {
            stk.push(tyr);
        } else {
            error( n.getLine(), n.getColumn(), "operator " + opName +" cannot be applied " + tyl.toString() + " and " + tyr.toString() );
        }
    }

    @Override
    public void visit(Add e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"+");
    }

    @Override
    public void visit(Sub e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"-");
    }

    @Override
    public void visit(Mult e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"*");
    }

    @Override
    public void visit(Div e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"/");
    }

    @Override
    public void visit(CModule e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if( tyr.match(tyint) && tyl.match(tyint) ) {
            stk.push(tyint);
        }
        else {
            error( e.getLine(), e.getColumn(), "operator % cannot be applied " + tyl.toString() + " and " + tyr.toString() );
        }
    }

    @Override
    public void visit(And e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if(tyr.match(tybool) && tyl.match(tybool)) {
            stk.push(tybool);
        }
        else {
            error( e.getLine(), e.getColumn(), "operator & cannot be applied " + tyl.toString() + " and " + tyr.toString() );
        }
    }

    @Override
    public void visit(Lt e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if(tyr.match(tyint) && tyl.match(tyint)) {
            stk.push(tybool);
        }
        else {
            if(tyr.match(tyfloat) && tyl.match(tyfloat)) {
                stk.push(tybool);
            }
            error( e.getLine(), e.getColumn(), "operator < cannot be applied " + tyl.toString() + " and " + tyr.toString() );
        }
    }

    @Override
    public void visit(Eq e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if(tyr.match(tyl)) {
            stk.push(tybool);
        }
        else {
            error( e.getLine(), e.getColumn(), "Incompatible type for ==" );
        }
    }

    @Override
    public void visit(Not e) {
        e.getExpression().accept(this);
        SType tyr = stk.pop();
        if(tyr.match(tybool)) {
            stk.push(tybool);
        }
        else {
            error( e.getLine(), e.getColumn(), "operator ! cannot be applied " + tyr.toString() );
        }
    }

    @Override
    public void visit(True e) {
        stk.push(tybool);
    }

    @Override
    public void visit(False e) {
        stk.push(tybool);
    }

    @Override
    public void visit(Int e) {
        stk.push(tyint);
    }

    @Override
    public void visit(FloatV e) {
        stk.push(tyfloat);
    }

    @Override
    public void visit(CallCmd e) {
        if(funcs.containsKey(e.getName())) {
            ArrayList<STyFunc> lista = funcs.get(e.getName());
            Expr[] par = e.getExpressions();
            SType[] param = null;
            if(par != null) {
                param = new SType[par.length];
                int i = 0;
                for(Expr p : par) {
                    p.accept(this);
                    param[i] = stk.pop();
                    i+=1;
                }
            }
            STyFunc aux = selecionaFunc(lista, param);
            if(aux != null) {
                if(e.getReturn()!=null) {
                    if(aux.getRetorno()!=null) {
                        SType[] retor = new SType[e.getReturn().length];
                        int i = 0;
                        for(LValue l : e.getReturn()) {
                            l.accept(this);
                            retor[i] = stk.pop();
                            i+=1;
                        }
                        if(check(retor, aux.getRetorno())) {
                            i = 0;
                            for(LValue l : e.getReturn()) {
                                if(retor[i].match(tyvar)) {
                                    if(aux.getRetorno()[i] instanceof STyNull) {
                                        error( e.getLine(), e.getColumn(), "null return!"  );
                                    }
                                    else {
                                        env.put(l.getId(),aux.getRetorno()[i]);
                                    }
                                    
                                }
                                i+=1;
                            }
                        }
                        else {
                            error( e.getLine(), e.getColumn(), "return types are wrong or number of variables different from return number!" );
                        }
                    }
                    else {
                        error( e.getLine(), e.getColumn(), "missing return statement!" );
                    }
                }
            }
            else {
                error( e.getLine(), e.getColumn(), "wrong parameter types!" );
            }
        }
        else {
            error( e.getLine(), e.getColumn(), "undeclared function!" );
        }
    }

    @Override
    public void visit(Attr e) {
        e.getValue().accept(this);
        SType auxID = stk.pop();
        e.getExpression().accept(this);
        SType aux = stk.pop();
        if(!aux.match(tyvar)) {
            if(auxID.match(tyvar)) {
                if(aux instanceof STyNull) {
                    error( e.getLine(), e.getColumn(), "null expr for undeclared variable!" );
                } else {
                    env.put(e.getValue().getId(), aux);
                }
            } else {
                if(!auxID.match(aux)) {
                    error( e.getLine(), e.getColumn(), "incompatible type!" );
                }
            }
        }
        else {
            error( e.getLine(), e.getColumn(), "undefined variable!" );
        }
    }

    @Override
    public void visit(If e) {
        e.getExpression().accept(this);
        if(stk.pop().match(tybool)) {
            e.getThen().accept(this);
            if (e.getElse() != null) {
                e.getElse().accept(this);
            }
        } else {
            error( e.getLine(), e.getColumn(), "IF must have type Bool!");
        }
    }

    @Override
    public void visit(Print e) {
        e.getExpression().accept(this);
        if(stk.pop().match(tyvar)) {
            error( e.getLine(), e.getColumn(), "undefined variable for print!");
        }
    }

    private boolean verificaRetornoIf(If cmdIf) {
        boolean left = false;
        boolean right = false;
        StmtList aux = null;
        If auxIf = null;
        if(cmdIf.getThen() instanceof Return) {
            left = true;
        }
        else {
            if(cmdIf.getThen() instanceof StmtList) {
                aux = (StmtList)cmdIf.getThen();
                left = verificaRetorno(aux.getList());
            } 
            else {
                if(cmdIf.getThen() instanceof If) {
                    auxIf = (If) cmdIf.getThen();
                    if(auxIf.getElse() != null) {
                        left = verificaRetornoIf(auxIf);
                    }
                }
            }
        }
        if(cmdIf.getElse() instanceof Return) {
            right = true;
        }
        else {
            if(cmdIf.getElse() instanceof StmtList) {
                aux = (StmtList)cmdIf.getElse();
                right = verificaRetorno(aux.getList());
            } 
            else {
                if(cmdIf.getElse() instanceof If) {
                    auxIf = (If) cmdIf.getElse();
                    if(auxIf.getElse() != null) {
                        right = verificaRetornoIf(auxIf);
                    }
                }
            }
        }
        return left && right;
    }

    private boolean verificaRetorno(Cmd[] comandos) {
        boolean resultado = false;
        StmtList aux = null;
        If auxIf = null;
        if(comandos != null) {
            for(Cmd c : comandos) {
                if(c instanceof Return) {
                    resultado = true;
                }
                if(c instanceof StmtList) {
                    aux = (StmtList) c;
                    resultado = verificaRetorno(aux.getList());
                }
            }
            if(!resultado) {
                for(Cmd c : comandos) {
                    if(c instanceof If) {
                        auxIf = (If) c;
                        if(auxIf.getElse() != null) {
                            resultado = verificaRetornoIf(auxIf);
                        }
                    }
                }
            }
        }
        return resultado;
    }

    @Override
    public void visit(Func f) { 
        if(f.getParam()!=null) {
            int i = 0;
            for(Param p : f.getParam()) {
                if(env.containsKey(p.getID())) {
                    error( f.getLine(), f.getColumn(), "duplicate method declaration");
                }
                else {
                    env.put(p.getID(), tempFunc.getParametro()[i]);
                }
                i+=1;
            }
        }
        if(f.getType()!=null) {
            if(!verificaRetorno(f.getBody())) {
                error( f.getLine(), f.getColumn(), "wrong return!");
            }
        }
        if(f.getBody()!=null) {
            for(Cmd c : f.getBody()) {
                c.accept(this);
            }
        }
    }

    @Override
    public void visit(Data d) { }

    @Override
    public void visit(Decl e) { }

    @Override
    public void visit(Return e) {
        if(tempFunc.getRetorno() != null) {
            if(e.getExpressions().length == tempFunc.getRetorno().length) {
                SType[] aux = new SType[e.getExpressions().length];
                int i = 0;
                for(Expr ex : e.getExpressions()) {
                    ex.accept(this);
                    aux[i] = stk.pop();
                    i+=1;
                }
                if(!checkReturn(aux, tempFunc.getRetorno())) {
                    error( e.getLine(), e.getColumn(), "wrong return types!");
                }
            }
            else {
                error( e.getLine(), e.getColumn(), "wrong return amount!");
            }
        }
        else {
            error( e.getLine(), e.getColumn(), "function cannot be returned!");
        }
    }

    @Override
    public void visit(Param e) { } 

    @Override
    public void visit(TyInt t) {
        stk.push(tyint);
    }

    @Override
    public void visit(TyFloat t) {
        stk.push(tyfloat);
    }

    @Override
    public void visit(TyBool t) {
        stk.push(tybool);
    }

    @Override
    public void visit(TyChar t) { 
        stk.push(tychar);
    }

    @Override
    public void visit(TyID t) { 
        if(datas.containsKey(t.getIdType())) {
            stk.push(datas.get(t.getIdType()));
        }
        else {
            error( t.getLine(), t.getColumn(), "Data " + t.getIdType() + " nonexistent!");
        }
    }

    @Override
    public void visit(Array e) { 
        SType aux = stk.pop();
        e.getIndex().accept(this);
        if(stk.pop().match(tyint)) {
            if (aux instanceof STyArr) {
                STyArr au = (STyArr)aux;
                aux = au.getArg();
                stk.push(aux);
            }
            else {
                error( e.getLine(), e.getColumn(), "wrong array access!" );
            }
        }
        else {
            error( e.getLine(), e.getColumn(), "expected integer type for array access!");
        }
    }

    @Override
    public void visit(CallExpr e) { 
        if(funcs.containsKey(e.getName())) {
            ArrayList<STyFunc> lista = funcs.get(e.getName());
            Expr[] par = e.getExpressions();
            SType[] param = null;
            if(par != null) {
                param = new SType[par.length];
                int i = 0;
                for(Expr p : par) {
                    p.accept(this);
                    param[i] = stk.pop();
                    i+=1;
                }
            }
            STyFunc aux = selecionaFunc(lista, param);
            if(aux != null) {
                e.getReturn().accept(this);
                if(stk.pop().match(tyint)) {
                    if(aux.getRetorno()!=null) {
                        if(e.getReturn() instanceof Int) {
                            Int n = (Int)e.getReturn();
                            int nu = n.getValue();
                            if(aux.getRetorno().length > nu && nu >= 0) {
                                stk.push(aux.getRetorno()[nu]);
                            }
                            else {
                                error( e.getLine(), e.getColumn(), "access undefined return!" );
                            }
                        }
                        else {
                            error( e.getLine(), e.getColumn(), "only integers in return access!" );
                        }
                    }
                    else {
                        error( e.getLine(), e.getColumn(), "undefined return!" );
                    }
                }
                else {
                    error( e.getLine(), e.getColumn(), "expected integer type!" );
                }
            }
            else {
                error( e.getLine(), e.getColumn(), "wrong parameter types or different number of parameters!" );
            }
        }
        else {
            error( e.getLine(), e.getColumn(), "undefined function!" );
        }
    }

    @Override
    public void visit(StmtList e) { 
        if(e.getList() != null) {
            for(Cmd c : e.getList()) {
                c.accept(this);
            }
        }
    }

    @Override
    public void visit(Iterate e) {
        e.getExpression().accept(this);
        if(stk.pop().match(tyint)) {
            e.getBody().accept(this);
        }
        else {
            error( e.getLine(), e.getColumn(), "expected integer type!");
        }
    }

    @Override
    public void visit(Char e) { 
        stk.push(tychar);
    }

    @Override
    public void visit(LValue e) { 
        if(env.containsKey(e.getId())) {
            stk.push(env.get(e.getId()));
            if(!e.getAccess().isEmpty()) {
                for(Access s : e.getAccess()) {
                    s.accept(this);
                }
            }
        }
        else {
            if(e.getAccess().isEmpty()) {
                stk.push(tyvar);
            }
            else {
                error( e.getLine(), e.getColumn(), "undeclared array or date variable!");
            }
        }
    }

    @Override
    public void visit(SMinus e) { 
        e.getExpression().accept(this);
        SType tyr = stk.pop();
        if(tyr.match(tyfloat)) {
            stk.push(tyfloat);
        }
        else {
            if(tyr.match(tyint)) {
                stk.push(tyint);
            }
            else {
                error( e.getLine(), e.getColumn(), "operator ! cannot be applied " + tyr.toString() );
            }
        }
    }

    @Override
    public void visit(Neq e) { 
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if(tyr.match(tyl)) {
            stk.push(tybool);
        }
        else {
            error( e.getLine(), e.getColumn(), "Incompatible type for !=" );
        }
    }

    @Override
    public void visit(New e) { 
        BType t = e.getType().getBtype();
        t.accept(this);
        SType aux = stk.pop();
        STyArr au = null;
        int numColchetes = e.getType().getBraces();
        if(e.getExpression()!=null) {
            e.getExpression().accept(this);
            if(stk.pop().match(tyint)) {
                numColchetes+=1;
                for(int i = 0; i < numColchetes; i++) {
                    au = new STyArr(aux);
                    aux = au;
                }
                stk.push(aux);
            }
            else {
                error( e.getLine(), e.getColumn(), "expected int type" );
            }
        }
        else {
            for(int i = 0; i < numColchetes; i++) {
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
        if(!ty.match(tyint)) {
            if(ty.match(tyvar)) {
                env.put(e.getValue().getId(), tyint);
            }
            else {
                error( e.getLine(), e.getColumn(), "expected int type!" );
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
        for(int i = 0; i < numColchetes; i++) {
            au = new STyArr(aux);
            aux = au;
        }
        stk.push(aux);
    }

    @Override
    public void visit(AccessData e) { 
        SType aux = stk.pop();
        if (aux instanceof STyData) {
            STyData au = (STyData)aux;
            if(au.elem.containsKey(e.getIndex())) {
                aux = au.elem.get(e.getIndex());
                stk.push(aux);
            }
            else {
                error( e.getLine(), e.getColumn(), "wrong access of Data element!" );
            }
        }
        else {
            error( e.getLine(), e.getColumn(), "wrong access!" );
        }
    }
}
