/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.parser;

import lang.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;
public class ConstroiASTVisitor extends langBaseVisitor
{
    @Override public SuperNode visitProg(langParser.ProgContext ctx)
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Data[] datas = null;
        Func[] funcs = null;
        Program noProg;
        SuperNode result = this.defaultResult();
        int n = ctx.data().size();
        if (n != 0)
        {
            datas = new Data[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.data(i);
                SuperNode childResult = c.accept(this);
                datas[i] = (Data) this.aggregateResult(result, childResult);
            }
        }
    
        result = this.defaultResult();
        n = ctx.func().size();
        if (n != 0)
        {
            funcs = new Func[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.func(i);
                SuperNode childResult = c.accept(this);
                funcs[i] = (Func) this.aggregateResult(result, childResult);
            }
        }
        noProg = new Program(line,column,datas, funcs);
        return noProg; 
    }

    @Override public SuperNode visitData(langParser.DataContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Decl[] decls = null;

        SuperNode result = this.defaultResult();
        int n = ctx.decl().size();
        if (n != 0)
        {
            decls = new Decl[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.decl(i);
                SuperNode childResult = c.accept(this);
                decls[i] = (Decl) this.aggregateResult(result, childResult);
            }
        }
        Data noData = new Data(line,column,ctx.IDtype().getText(), decls);
        return noData;
    }

    @Override public SuperNode visitDecl(langParser.DeclContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Tipo t = (Tipo) ctx.type().accept(this);
        Decl noDecl = null;
        if (ctx.ID().getText() != null)
        {
            noDecl = new Decl(line, column,ctx.ID().getText(), t);
        }
        else
        {
            noDecl = new Decl(line,column,ctx.IDtype().getText(), t);
        }
        
        return noDecl; 
    }

    @Override public SuperNode visitFunc(langParser.FuncContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Param[] params = null;
        Tipo[] retorno = null;
        Cmd[] comandos = null;
        SuperNode result = this.defaultResult();
        if(ctx.params()!= null) {
            int n = ctx.params().param().size();
            if (n != 0)
            {
                params = new Param[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.params().param(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Param) this.aggregateResult(result, childResult);
                }
            }
        }

        result = this.defaultResult();
        int n = ctx.type().size();
        if (n != 0)
        {
            retorno = new Tipo[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.type(i);
                SuperNode childResult = c.accept(this);
                retorno[i] = (Tipo) this.aggregateResult(result, childResult);
            }
        }

        result = this.defaultResult();
        n = ctx.cmd().size();
        if (n != 0)
        {
            comandos = new Cmd[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.cmd(i);
                SuperNode childResult = c.accept(this);
                comandos[i] = (Cmd) this.aggregateResult(result, childResult);
            }
        }
        Func noFunc = null;
        if (ctx.ID().getText() != null)
        {
            noFunc = new Func(line,column,ctx.ID().getText(), params, retorno, comandos);
        }
        else
        {
            noFunc = new Func(line,column,ctx.IDtype().getText(), params, retorno, comandos);
        }
        
        return noFunc;
    }

	@Override public SuperNode visitParams(langParser.ParamsContext ctx) { return visitChildren(ctx); }
	
    @Override public SuperNode visitParam(langParser.ParamContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Tipo t = (Tipo) ctx.type().accept(this);
        Param noParam = null;
        if (ctx.ID().getText() != null)
        {
            noParam = new Param(line,column,ctx.ID().getText(), t);
        }
        else
        {
            noParam = new Param(line,column,ctx.IDtype().getText(), t);
        }

        return noParam;
    }
	
    @Override public SuperNode visitType(langParser.TypeContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Btype noBtype = (Btype) ctx.btype().accept(this);
        int colchetes = ctx.bracks().size();

        Tipo tip = new Tipo(line,column,noBtype, colchetes);
        return tip;
    }

    @Override public SuperNode visitIntType(langParser.IntTypeContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyInt tyInt = new TyInt(line,column);
        return tyInt;
    }
	
    @Override public SuperNode visitCharType(langParser.CharTypeContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyChar tyChar = new TyChar(line,column);
        return tyChar;
    }
    
    
    @Override public SuperNode visitBoolType(langParser.BoolTypeContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyBool tyBool = new TyBool(line,column);
        return tyBool;
    }
	
    @Override public SuperNode visitFloatType(langParser.FloatTypeContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyFloat tyFloat = new TyFloat(line,column);
        return tyFloat;
    }
	
    @Override public SuperNode visitIdType(langParser.IdTypeContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyID tyID = new TyID(line,column,ctx.IDtype().getText());
        return tyID;
    }

    @Override public SuperNode visitCmdList(langParser.CmdListContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Cmd[] list = null;
        SuperNode result = this.defaultResult();
        int n = ctx.cmd().size();
        if (n != 0)
        {
            list = new Cmd[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.cmd(i);
                SuperNode childResult = c.accept(this);
                list[i] = (Cmd) this.aggregateResult(result, childResult);
            }
        }
        CmdList lista = new CmdList(line,column,list);
        return lista;
    }
	
    @Override public SuperNode visitIf(langParser.IfContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr exp = (Expr) ctx.exp().accept(this);
        Cmd thn = (Cmd) ctx.cmd().accept(this);

        If cIf = new If(line,column,exp, thn);
        return cIf;
    }
	
    @Override public SuperNode visitIfElse(langParser.IfElseContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr exp = (Expr) ctx.exp().accept(this);
        Cmd thn = (Cmd) ctx.cmd(0).accept(this);
        Cmd els = (Cmd) ctx.cmd(1).accept(this);

        If cIf = new If(line,column,exp, thn, els);
        return cIf;
    }

    @Override public SuperNode visitIterate(langParser.IterateContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr teste = (Expr) ctx.exp().accept(this);
        Cmd body = (Cmd) ctx.cmd().accept(this);

        Iterate itera = new Iterate(line,column,teste,body);
        return itera;
    }

    @Override public SuperNode visitRead(langParser.ReadContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue var = (Lvalue) ctx.lvalue().accept(this);

        Read leitura = new Read(line,column,var);
        return leitura;
    }
	
    @Override public SuperNode visitPrint(langParser.PrintContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr exp = (Expr) ctx.exp().accept(this);

        Print imprimir = new Print(line,column,exp);
        return imprimir;
    }

    @Override public SuperNode visitReturn(langParser.ReturnContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr[] retornos = null;
        SuperNode result = this.defaultResult();
        int n = ctx.exp().size();
        if (n != 0)
        {
            retornos = new Expr[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.exp(i);
                SuperNode childResult = c.accept(this);
                retornos[i] = (Expr) this.aggregateResult(result, childResult);
            }
        }
        Return ret = new Return(line,column,retornos);
        return ret;
    }
	
    @Override public SuperNode visitAtribuir(langParser.AtribuirContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue var = (Lvalue) ctx.lvalue().accept(this);
        Expr exp = (Expr) ctx.exp().accept(this);
        Attr atribui = new Attr(line,column,var, exp);
        return atribui;
    }
	
    @Override public SuperNode visitCallCmd(langParser.CallCmdContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr[] params = null;
        Lvalue[] vars = null;
        SuperNode result = this.defaultResult();
        if(ctx.exps()!= null) {
            int n = ctx.exps().exp().size();
            if (n != 0)
            {
                params = new Expr[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Expr) this.aggregateResult(result, childResult);
                }
            }
        }

        result = this.defaultResult();
        if(ctx.lvalue() != null)
        {
            int n = ctx.lvalue().size();
            if (n != 0)
            {
                vars = new Lvalue[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.lvalue(i);
                    SuperNode childResult = c.accept(this);
                    vars[i] = (Lvalue) this.aggregateResult(result, childResult);
                }
            }
        }
        
        Call noCall = null;
        if (ctx.ID().getText() != null)
        {
            noCall = new Call(line,column,ctx.ID().getText(), params, vars);
        }
        else
        {
            noCall = new Call(line,column,ctx.IDtype().getText(), params, vars);
        }
        
        return noCall;
    }

	@Override public SuperNode visitRex(langParser.RexContext ctx) { return visitChildren(ctx); }

    @Override public SuperNode visitAnd(langParser.AndContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.exp(0).accept(this);
        Expr right = (Expr) ctx.exp(1).accept(this);

        And noAnd = new And(line,column,left, right);
        return noAnd;
    }
	
    @Override public SuperNode visitEqual(langParser.EqualContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.rexp().accept(this);
        Expr right = (Expr) ctx.aexp().accept(this);

        Eq noEq = new Eq(line,column,left, right);
        return noEq;
    }
    
	@Override public SuperNode visitAex(langParser.AexContext ctx) { return visitChildren(ctx); }

    @Override public SuperNode visitLt(langParser.LtContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.aexp(0).accept(this);
        Expr right = (Expr) ctx.aexp(1).accept(this);

        Lt noLt = new Lt(line,column,left, right);
        return noLt;
    }

    @Override public SuperNode visitNotEqual(langParser.NotEqualContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.rexp().accept(this);
        Expr right = (Expr) ctx.aexp().accept(this);

        NEq noNEq = new NEq(line,column,left, right);
        return noNEq;
    }
    
    @Override public SuperNode visitAdd(langParser.AddContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.aexp().accept(this);
        Expr right = (Expr) ctx.mexp().accept(this);

        Add noAdd = new Add(line,column,left, right);
        return noAdd;
    }
	
	@Override public SuperNode visitMex(langParser.MexContext ctx) { return visitChildren(ctx); }

    @Override public SuperNode visitSub(langParser.SubContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.aexp().accept(this);
        Expr right = (Expr) ctx.mexp().accept(this);

        Sub noSub = new Sub(line,column,left, right);
        return noSub;
    }

    @Override public SuperNode visitDiv(langParser.DivContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.mexp().accept(this);
        Expr right = (Expr) ctx.sexp().accept(this);

        Div noDiv = new Div(line,column,left, right);
        return noDiv;
    }
	
    @Override public SuperNode visitMod(langParser.ModContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.mexp().accept(this);
        Expr right = (Expr) ctx.sexp().accept(this);

        Mod noMod = new Mod(line,column,left, right);
        return noMod;
    }
	
    @Override public SuperNode visitMul(langParser.MulContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr left = (Expr) ctx.mexp().accept(this);
        Expr right = (Expr) ctx.sexp().accept(this);

        Mul noMul = new Mul(line,column,left, right);
        return noMul;
    }
	
	@Override public SuperNode visitSex(langParser.SexContext ctx) { return visitChildren(ctx); }

    @Override public SuperNode visitNot(langParser.NotContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr exp = (Expr) ctx.sexp().accept(this);

        Not noNot = new Not(line,column,exp);
        return noNot; 
    }
	
    @Override public SuperNode visitNeg(langParser.NegContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr exp = (Expr) ctx.sexp().accept(this);

        Neg noNeg = new Neg(line,column,exp);
        return noNeg; 
    }
	
    @Override public SuperNode visitTrue(langParser.TrueContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        True verd = new True(line,column);
        return verd;
    }
	
    @Override public SuperNode visitFalse(langParser.FalseContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        False fals = new False(line,column);
        return fals;
    }
	
    @Override public SuperNode visitNull(langParser.NullContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Null nul = new Null(line,column);
        return nul;
    }
	
    @Override public SuperNode visitInt(langParser.IntContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        NInt noInt = new NInt( line,column,Integer.parseInt(ctx.INT_LITERAL().getText()));
        return noInt;
    }
	
    @Override public SuperNode visitFloat(langParser.FloatContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        NFloat noFloat = new NFloat( line,column,Float.parseFloat(ctx.FLOAT_LITERAL().getText()));
        return noFloat;
    }
	
    @Override public SuperNode visitChar(langParser.CharContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        String s = ctx.CHAR_LITERAL().getText();

        String newString =  s.replace("'\\\\'", "'\\'");
        newString = newString.replace("'\\n'", "'\n'");
        newString = newString.replace("'\\r'", "'\r'");
        newString = newString.replace("'\\t'", "'\t'");
        newString = newString.replace("'\\b'", "'\b'");
        newString = newString.replace("'\\''", "'''");
        newString = newString.replace("'\\\"'", "'\"'");
        Character c = newString.charAt(1);

        LChar noChar = new LChar(line,column,c);
        return noChar;
    }
	
	@Override public SuperNode visitPex(langParser.PexContext ctx) { return visitChildren(ctx); }
	
    @Override public SuperNode visitLval(langParser.LvalContext ctx) { return visitChildren(ctx); }
	
    @Override public SuperNode visitEx(langParser.ExContext ctx) 
    { 
        Expr e = (Expr) ctx.exp().accept(this);
        return e;
    }
	
    @Override public SuperNode visitNew(langParser.NewContext ctx) 
    {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Tipo t = (Tipo)  ctx.type().accept(this);
        Expr e = null;
        if(ctx.exp()!= null) {
            e = (Expr) ctx.exp().accept(this);
        }
        New noNew = new New(line,column, t, e);
        return noNew;
    }
	
    @Override public SuperNode visitCallExp(langParser.CallExpContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Expr ret = (Expr) ctx.exp().accept(this);
        Expr[] params = null;
        SuperNode result = this.defaultResult();
        if(ctx.exps()!= null) {
            int n = ctx.exps().exp().size();
            if (n != 0)
            {
                params = new Expr[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Expr) this.aggregateResult(result, childResult);
                }
            }
        }
        CallExpr noCallExpr = null;
        if (ctx.ID().getText() != null)
        {
            noCallExpr = new CallExpr(line,column,ctx.ID().getText(), params, ret);
        }
        else
        {
            noCallExpr = new CallExpr(line,column,ctx.IDtype().getText(), params, ret);
        }
        
        return noCallExpr; 
    }
	
    @Override public SuperNode visitSeletorData(langParser.SeletorDataContext ctx) 
    {  
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue l = (Lvalue) ctx.lvalue().accept(this);
        if (ctx.ID().getText() != null)
        {
            l.add(new DataSeletor(line,column,ctx.ID().getText()));
        }
        else
        {
            l.add(new DataSeletor(line,column,ctx.IDtype().getText()));
        }
        return l; 
    }
	
    @Override public SuperNode visitLvalueID(langParser.LvalueIDContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue var = null;
        if (ctx.ID().getText() != null)
        {
            var = new Lvalue(line,column,ctx.ID().getText());
        }
        else
        {
            var = new Lvalue(line,column,ctx.IDtype().getText());
        }
        return var; 
    }
	
    @Override public SuperNode visitSeletorArray(langParser.SeletorArrayContext ctx) 
    { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue l = (Lvalue) ctx.lvalue().accept(this);
        Expr e = (Expr) ctx.exp().accept(this);

        l.add(new ArraySeletor(line,column,e));
        return l; 
    }
	
	@Override public SuperNode visitExps(langParser.ExpsContext ctx) { return visitChildren(ctx); }
}
