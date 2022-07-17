/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.visitors;

import lang.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;
import lang.parser.*;

public class BuildASTVisitors extends langBaseVisitor<SuperNode> {

    @Override 
	public SuperNode visitProgram(langParser.ProgramContext e) {
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Data[] datas = null;
        Func[] funcs = null;
        Program nodeProgram;
        SuperNode result = this.defaultResult();
        int n = e.data().size();
        if (n != 0) {
            datas = new Data[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.data(i);
                SuperNode childResult = c.accept(this);
                datas[i] = (Data) this.aggregateResult(result, childResult);
            }
        }
        result = this.defaultResult();
        n = e.func().size();
        if (n != 0) {
            funcs = new Func[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.func(i);
                SuperNode childResult = c.accept(this);
                funcs[i] = (Func) this.aggregateResult(result, childResult);
            }
        }
        nodeProgram = new Program(line,column,datas, funcs);
        return nodeProgram; 
    }

	@Override 
	public SuperNode visitData(langParser.DataContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        TData[] declaration = null;
        SuperNode result = this.defaultResult();
        int n = e.tdata().size();
        if (n != 0) {
            declaration = new TData[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.tdata(i);
                SuperNode childResult = c.accept(this);
                declaration[i] = (TData) this.aggregateResult(result, childResult);
            }
        }
        Data nodeData = new Data(line,column, e.IDTYPE().getText(), declaration);
        return nodeData;
	}


	@Override 
	public SuperNode visitTdata(langParser.TdataContext e)  { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Type type = (Type) e.type().accept(this);
        TData nodeTData = null;
        if (e.ID().getText() != null) {
            nodeTData = new TData(line, column,e.ID().getText(), type);
        } else {
            nodeTData = new TData(line,column,e.IDTYPE().getText(), type);
        }
        return nodeTData; 
	}


	@Override 
	public SuperNode visitFunc(langParser.FuncContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Param[] params = null;
        Type[] returnable = null;
        Cmd[] comand = null;
        SuperNode result = this.defaultResult();
        if(e.params()!= null) {
            int n = e.params().param().size();
            if (n != 0) {
                params = new Param[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                    ParseTree c = e.params().param(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Param) this.aggregateResult(result, childResult);
                }
            }
        }
        result = this.defaultResult();
        int n = e.type().size();
        if (n != 0) {
            returnable = new Type[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.type(i);
                SuperNode childResult = c.accept(this);
                returnable[i] = (Type) this.aggregateResult(result, childResult);
            }
        }
        result = this.defaultResult();
        n = e.cmd().size();
        if (n != 0) {
            comand = new Cmd[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.cmd(i);
                SuperNode childResult = c.accept(this);
                comand[i] = (Cmd) this.aggregateResult(result, childResult);
            }
        }
        Func nodeFunc = null;
        if (e.ID().getText() != null) {
            nodeFunc = new Func(line, column, e.ID().getText(), params, returnable, comand);
        } else {
            nodeFunc = new Func(line,column,e.IDTYPE().getText(), params, returnable, comand);
        }
        
        return nodeFunc;
	}

	@Override public SuperNode visitParams(langParser.ParamsContext e) { 
        return visitChildren(e); 
    }

	@Override 
	public SuperNode visitParam(langParser.ParamContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Type type = (Type) e.type().accept(this);
        Param nodeParam = null;
        if (e.ID().getText() != null) {
            nodeParam = new Param(line, column, e.ID().getText(), type);
        } else {
            nodeParam = new Param(line, column, e.IDTYPE().getText(), type);
        }
        return nodeParam;
	}
    
    @Override 
    public SuperNode visitType(langParser.TypeContext e) { 
         int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        BType nodeBType = (BType) e.btype().accept(this);
        int braces = e.brace().size();
        Type nodeType = new Type(line, column, nodeBType, braces);
        return nodeType; 
    }

	@Override 
    public SuperNode visitBrace(langParser.BraceContext e) { 
        return visitChildren(e); 
    }

    @Override 
	public SuperNode visitTyID(langParser.TyIDContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        TyID nodeTyID = new TyID(line,column,e.IDTYPE().getText());
        return nodeTyID; 
	}
	
	@Override 
	public SuperNode visitTyInt(langParser.TyIntContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        TyInt nodeTyInt = new TyInt(line,column);
        return nodeTyInt;
	}
	
	@Override 
	public SuperNode visitTyChar(langParser.TyCharContext e) {     
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        TyChar nodeTyChar = new TyChar(line,column);
        return nodeTyChar;
	}

	@Override 
	public SuperNode visitTyBool(langParser.TyBoolContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        TyBool nodeTyBool = new TyBool(line,column);
        return nodeTyBool;
	}


	@Override 
	public SuperNode visitTyFloat(langParser.TyFloatContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        TyFloat nodeTyFloat = new TyFloat(line,column);
        return nodeTyFloat;
	}

	@Override 
	public SuperNode visitStmtList(langParser.StmtListContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Cmd[] stmtList = null;
        SuperNode result = this.defaultResult();
        int n = e.cmd().size();
        if (n != 0) {
            stmtList = new Cmd[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.cmd(i);
                SuperNode childResult = c.accept(this);
                stmtList[i] = (Cmd) this.aggregateResult(result, childResult);
            }
        }
        StmtList nodeStmtList = new StmtList(line,column,stmtList);
        return nodeStmtList;
	}
	
	@Override 
	public SuperNode visitIf(langParser.IfContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr exp = (Expr) e.exp().accept(this);
        Cmd the = (Cmd) e.cmd().accept(this);
        If nodeIf = new If(line, column, exp, the);
        return nodeIf;
	}
	
	@Override 
	public SuperNode visitIfElse(langParser.IfElseContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr exp = (Expr) e.exp().accept(this);
        Cmd the = (Cmd) e.cmd(0).accept(this);
        Cmd els = (Cmd) e.cmd(1).accept(this);
        If nodeIfElse = new If(line, column, exp, the, els);
        return nodeIfElse;
	}
	
	@Override 
	public SuperNode visitIterate(langParser.IterateContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr exp = (Expr) e.exp().accept(this);
        Cmd cmd = (Cmd) e.cmd().accept(this);
        Iterate nodeIterate = new Iterate(line, column, exp, cmd);
        return nodeIterate;
	}

	@Override 
	public SuperNode visitRead(langParser.ReadContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        LValue lvalue = (LValue) e.lvalue().accept(this);
        Read nodeRead = new Read(line, column, lvalue);
        return nodeRead;
	}
	
	@Override 
	public SuperNode visitPrint(langParser.PrintContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr exp = (Expr) e.exp().accept(this);
        Print nodePrint = new Print(line, column, exp);
        return nodePrint;
	}
		
	@Override 
	public SuperNode visitReturn(langParser.ReturnContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr[] expressions = null;
        SuperNode result = this.defaultResult();
        int n = e.exp().size();
        if (n != 0) {
            expressions = new Expr[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.exp(i);
                SuperNode childResult = c.accept(this);
                expressions[i] = (Expr) this.aggregateResult(result, childResult);
            }
        }
        Return nodeReturn = new Return(line, column, expressions);
        return nodeReturn;
	}
	
	@Override 
	public SuperNode visitAttr(langParser.AttrContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        LValue lvalue = (LValue) e.lvalue().accept(this);
        Expr exp = (Expr) e.exp().accept(this);
        Attr nodeAttr = new Attr(line, column, lvalue, exp);
        return nodeAttr;
	}


	@Override 
	public SuperNode visitCallCMD(langParser.CallCMDContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr[] expressions = null;
        LValue[] lvalues = null;
        SuperNode result = this.defaultResult();
        if(e.exps()!= null) {
            int n = e.exps().exp().size();
            if (n != 0) {
                expressions = new Expr[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                    ParseTree c = e.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    expressions[i] = (Expr) this.aggregateResult(result, childResult);
                }
            }
        }
        result = this.defaultResult();
        if(e.lvalue() != null) {
            int n = e.lvalue().size();
            if (n != 0) {
                lvalues = new LValue[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                    ParseTree c = e.lvalue(i);
                    SuperNode childResult = c.accept(this);
                    lvalues[i] = (LValue) this.aggregateResult(result, childResult);
                }
            }
        }
        
        CallCmd nodeCall = null;
        if (e.ID().getText() != null) {
            nodeCall = new CallCmd(line,column,e.ID().getText(), expressions, lvalues);
        }
        else {
            nodeCall = new CallCmd(line,column,e.IDTYPE().getText(), expressions, lvalues);
        }
        return nodeCall;
	}

    @Override 
	public SuperNode visitCallExpr(langParser.CallExprContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr exp = (Expr) e.exp().accept(this);
        Expr[] params = null;
        SuperNode result = this.defaultResult();
        if(e.exps()!= null) {
            int n = e.exps().exp().size();
            if (n != 0) {
                params = new Expr[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                    ParseTree c = e.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Expr) this.aggregateResult(result, childResult);
                }
            }
        }
        CallExpr nodeCallExpr = null;
        if (e.ID().getText() != null) {
            nodeCallExpr = new CallExpr(line,column,e.ID().getText(), params, exp);
        }
        else {
            nodeCallExpr = new CallExpr(line,column,e.IDTYPE().getText(), params, exp);
        }
        return nodeCallExpr; 
	}
	
	@Override public SuperNode visitRex(langParser.RexContext e) { 
        return visitChildren(e); 
    }

	@Override 
	public SuperNode visitAnd(langParser.AndContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.exp(0).accept(this);
        Expr right = (Expr) e.exp(1).accept(this);
        And nodeAnd = new And(line, column, left, right);
        return nodeAnd;
	}

	@Override 
	public SuperNode visitAex(langParser.AexContext e) { 
        return visitChildren(e); 
    }
	
	@Override 
	public SuperNode visitLess(langParser.LessContext e) { 
		    int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.aexp(0).accept(this);
        Expr right = (Expr) e.aexp(1).accept(this);
        Less nodeLess = new Less(line, column, left, right);
        return nodeLess;
	}
	
	@Override 
	public SuperNode visitNeq(langParser.NeqContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.rexp().accept(this);
        Expr right = (Expr) e.aexp().accept(this);

        Neq nodeNeq = new Neq(line, column, left, right);
        return nodeNeq; 
	}

	@Override 
	public SuperNode visitEq(langParser.EqContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.rexp().accept(this);
        Expr right = (Expr) e.aexp().accept(this);
        Eq nodeEq = new Eq(line, column, left, right);
        return nodeEq;
	}
	
	@Override 
	public SuperNode visitMex(langParser.MexContext e) { 
        return visitChildren(e); 
    }

	@Override 
	public SuperNode visitSub(langParser.SubContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.aexp().accept(this);
        Expr right = (Expr) e.mexp().accept(this);
        Sub nodeSub= new Sub(line, column, left, right);
        return nodeSub;
	}
	
	@Override 
	public SuperNode visitAdd(langParser.AddContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.aexp().accept(this);
        Expr right = (Expr) e.mexp().accept(this);
        Add nodeAdd = new Add(line, column, left, right);
        return nodeAdd;
	}
	
	@Override 
	public SuperNode visitDiv(langParser.DivContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.mexp().accept(this);
        Expr right = (Expr) e.sexp().accept(this);
        Div nodeDiv = new Div(line, column, left, right);
        return nodeDiv; 
	}

	@Override 
	public SuperNode visitMult(langParser.MultContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.mexp().accept(this);
        Expr right = (Expr) e.sexp().accept(this);
        Mult nodeMult = new Mult(line, column, left, right);
        return nodeMult;
	}
	
	@Override 
	public SuperNode visitSex(langParser.SexContext e) { 
        return visitChildren(e); 
    }

	@Override 
	public SuperNode visitMod(langParser.ModContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr left = (Expr) e.mexp().accept(this);
        Expr right = (Expr) e.sexp().accept(this);
        CModule nodeCModule = new CModule(line, column, left, right);
        return nodeCModule;
	}
	
	@Override 
	public SuperNode visitNot(langParser.NotContext e) { 
	    int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr exp = (Expr) e.sexp().accept(this);
        Not nodeNot = new Not(line, column, exp);
        return nodeNot;  
	}
	
	@Override 
	public SuperNode visitSMinus(langParser.SMinusContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Expr exp = (Expr) e.sexp().accept(this);
        SMinus nodeSMinus= new SMinus(line, column, exp);
        return nodeSMinus;
	}

	@Override 
	public SuperNode visitTrue(langParser.TrueContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        True nodeTrue = new True(line, column);
        return nodeTrue;
	}

	@Override 
	public SuperNode visitFalse(langParser.FalseContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        False nodeFalse = new False(line, column);
        return nodeFalse;
	}

	@Override 
	public SuperNode visitNull(langParser.NullContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Null nodeNull = new Null(line, column);
        return nodeNull;
	}

	@Override 
	public SuperNode visitInt(langParser.IntContext e) { 
		int line = e.getStart().getLine();
		int column = e.getStart().getCharPositionInLine();
        Int nodeInt = new Int( line, column, Integer.parseInt(e.INT().getText()));
        return nodeInt; 
	}
	
	@Override 
	public SuperNode visitFloat(langParser.FloatContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        FloatV nodeFloat = new FloatV( line,column,Float.parseFloat(e.FLOAT().getText()));
        return nodeFloat;
	}

	@Override 
	public SuperNode visitCaracter(langParser.CaracterContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        String s = e.CARACTER().getText();
        String newString =  s.replace("'\\\\'", "'\\'");
        newString = newString.replace("'\\n'", "'\n'");
        newString = newString.replace("'\\r'", "'\r'");
        newString = newString.replace("'\\t'", "'\t'");
        newString = newString.replace("'\\b'", "'\b'");
        newString = newString.replace("'\\''", "'''");
        newString = newString.replace("'\\\"'", "'\"'");
        Character c = newString.charAt(1);
        Caracter nodeCaracter = new Caracter(line, column, c);
        return nodeCaracter;
	}

	@Override public SuperNode visitPex(langParser.PexContext e) { 
        return visitChildren(e); 
    }

	@Override public SuperNode visitLValues(langParser.LValuesContext e) { 
        return visitChildren(e);
    }
	
	@Override 
	public SuperNode visitExpression(langParser.ExpressionContext e) { 
		Expr nodeExpression = (Expr) e.exp().accept(this);
        return nodeExpression;
	}

	@Override 
	public SuperNode visitNew(langParser.NewContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Type type = (Type) e.type().accept(this);
        Expr exp = null;
        if(e.exp()!= null) {
            exp = (Expr) e.exp().accept(this);
        }
        New nodeNew = new New(line, column, type, exp);
        return nodeNew;
	}

	@Override 
	public SuperNode visitLValueIDs(langParser.LValueIDsContext e) { 
        int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        LValue nodeLValue = null;
        if (e.ID().getText() != null) {
            nodeLValue = new LValue(line, column, e.ID().getText());
        } else {
            nodeLValue = new LValue(line, column, e.IDTYPE().getText());
        }
        return nodeLValue; 
	}

	@Override 
	public SuperNode visitLData(langParser.LDataContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        LValue nodeLData = (LValue) e.lvalue().accept(this);
        if (e.ID().getText() != null) {
            nodeLData.add(new LData(line, column, e.ID().getText()));
        } else {
            nodeLData.add(new LData(line, column, e.IDTYPE().getText()));
        }
        return nodeLData; 
	}

	@Override 
	public SuperNode visitLExpr(langParser.LExprContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        LValue nodeLExp = (LValue) e.lvalue().accept(this);
        Expr exp = (Expr) e.exp().accept(this);
        nodeLExp.add(new LExpr(line, column, exp));
        return nodeLExp; 
	}

	@Override 
    public SuperNode visitExps(langParser.ExpsContext e) { 
        return visitChildren(e); 
    }
}

