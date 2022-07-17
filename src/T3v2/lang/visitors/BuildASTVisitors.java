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
        Decl[] declaration = null;
        SuperNode result = this.defaultResult();
        int n = e.decl().size();
        if (n != 0) {
            declaration = new Decl[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.decl(i);
                SuperNode childResult = c.accept(this);
                declaration[i] = (Decl) this.aggregateResult(result, childResult);
            }
        }
        Data nodeData = new Data(line,column, e.IDTYPE().getText(), declaration);
        return nodeData;
	}


	@Override 
	public SuperNode visitDecl(langParser.DeclContext e)  { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Type type = (Type) e.type().accept(this);
        Decl nodeDecl = null;
        if (e.ID().getText() != null) {
            nodeDecl = new Decl(line, column,e.ID().getText(), type);
        } else {
            nodeDecl = new Decl(line,column,e.IDTYPE().getText(), type);
        }
        return nodeDecl; 
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
        Exp expression = (Exp) e.exp().accept(this);
        Cmd the = (Cmd) e.cmd().accept(this);
        If nodeIf = new If(line, column, expression, the);
        return nodeIf;
	}
	
	@Override 
	public SuperNode visitIfElse(langParser.IfElseContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp expression = (Exp) e.exp().accept(this);
        Cmd the = (Cmd) e.cmd(0).accept(this);
        Cmd els = (Cmd) e.cmd(1).accept(this);
        If nodeIfElse = new If(line, column, expression, the, els);
        return nodeIfElse;
	}
	
	@Override 
	public SuperNode visitIterate(langParser.IterateContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp expression = (Exp) e.exp().accept(this);
        Cmd cmd = (Cmd) e.cmd().accept(this);
        Iterate nodeIterate = new Iterate(line, column, expression, cmd);
        return nodeIterate;
	}

	@Override 
	public SuperNode visitRead(langParser.ReadContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Var var = (Var) e.var().accept(this);
        Read nodeRead = new Read(line, column, var);
        return nodeRead;
	}
	
	@Override 
	public SuperNode visitPrint(langParser.PrintContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp expression = (Exp) e.exp().accept(this);
        Print nodePrint = new Print(line, column, expression);
        return nodePrint;
	}
		
	@Override 
	public SuperNode visitReturn(langParser.ReturnContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp[] expressions = null;
        SuperNode result = this.defaultResult();
        int n = e.exp().size();
        if (n != 0) {
            expressions = new Exp[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                ParseTree c = e.exp(i);
                SuperNode childResult = c.accept(this);
                expressions[i] = (Exp) this.aggregateResult(result, childResult);
            }
        }
        Return nodeReturn = new Return(line, column, expressions);
        return nodeReturn;
	}
	
	@Override 
	public SuperNode visitAttr(langParser.AttrContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Var var = (Var) e.var().accept(this);
        Exp expression = (Exp) e.exp().accept(this);
        Attr nodeAttr = new Attr(line, column, var, expression);
        return nodeAttr;
	}


	@Override 
	public SuperNode visitCallCMD(langParser.CallCMDContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp[] expressions = null;
        Var[] vars = null;
        SuperNode result = this.defaultResult();
        if(e.exps()!= null) {
            int n = e.exps().exp().size();
            if (n != 0) {
                expressions = new Exp[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                    ParseTree c = e.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    expressions[i] = (Exp) this.aggregateResult(result, childResult);
                }
            }
        }
        result = this.defaultResult();
        if(e.var() != null) {
            int n = e.var().size();
            if (n != 0) {
                vars = new Var[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                    ParseTree c = e.var(i);
                    SuperNode childResult = c.accept(this);
                    vars[i] = (Var) this.aggregateResult(result, childResult);
                }
            }
        }
        
        CallCmd nodeCall = null;
        if (e.ID().getText() != null) {
            nodeCall = new CallCmd(line,column,e.ID().getText(), expressions, vars);
        }
        else {
            nodeCall = new CallCmd(line,column,e.IDTYPE().getText(), expressions, vars);
        }
        return nodeCall;
	}
	
	@Override public SuperNode visitRex(langParser.RexContext e) { 
        return visitChildren(e); 
    }

	@Override 
	public SuperNode visitAnd(langParser.AndContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp left = (Exp) e.exp(0).accept(this);
        Exp right = (Exp) e.exp(1).accept(this);
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
        Exp left = (Exp) e.aexp(0).accept(this);
        Exp right = (Exp) e.aexp(1).accept(this);
        Less nodeLess = new Less(line, column, left, right);
        return nodeLess;
	}
	
	@Override 
	public SuperNode visitNeq(langParser.NeqContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp left = (Exp) e.rexp().accept(this);
        Exp right = (Exp) e.aexp().accept(this);

        Neq nodeNeq = new Neq(line, column, left, right);
        return nodeNeq; 
	}

	@Override 
	public SuperNode visitEq(langParser.EqContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp left = (Exp) e.rexp().accept(this);
        Exp right = (Exp) e.aexp().accept(this);
        Eq nodeEq = new Eq(line, column, left, right);
        return nodeEq;
	}
	
	@Override 
	public SuperNode visitMex(langParser.MexContext e) { 
        return visitChildren(e); 
    }

	@Override 
	public SuperNode visitMinus(langParser.MinusContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp left = (Exp) e.aexp().accept(this);
        Exp right = (Exp) e.mexp().accept(this);
        Minus nodeMinus= new Minus(line, column, left, right);
        return nodeMinus;
	}
	
	@Override 
	public SuperNode visitPlus(langParser.PlusContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp left = (Exp) e.aexp().accept(this);
        Exp right = (Exp) e.mexp().accept(this);
        Plus nodePlus = new Plus(line, column, left, right);
        return nodePlus;
	}
	
	@Override 
	public SuperNode visitDiv(langParser.DivContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp left = (Exp) e.mexp().accept(this);
        Exp right = (Exp) e.sexp().accept(this);
        Div nodeDiv = new Div(line, column, left, right);
        return nodeDiv; 
	}

	@Override 
	public SuperNode visitMult(langParser.MultContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp left = (Exp) e.mexp().accept(this);
        Exp right = (Exp) e.sexp().accept(this);
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
        Exp left = (Exp) e.mexp().accept(this);
        Exp right = (Exp) e.sexp().accept(this);
        CModule nodeCModule = new CModule(line, column, left, right);
        return nodeCModule;
	}
	
	@Override 
	public SuperNode visitNot(langParser.NotContext e) { 
	    int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp expression = (Exp) e.sexp().accept(this);
        Not nodeNot = new Not(line, column, expression);
        return nodeNot;  
	}
	
	@Override 
	public SuperNode visitSMinus(langParser.SMinusContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp expression = (Exp) e.sexp().accept(this);
        SMinus nodeSMinus= new SMinus(line, column, expression);
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

	@Override public SuperNode visitVars(langParser.VarsContext e) { 
        return visitChildren(e);
    }
	
	@Override 
	public SuperNode visitExpression(langParser.ExpressionContext e) { 
		Exp nodeExpression = (Exp) e.exp().accept(this);
        return nodeExpression;
	}

	@Override 
	public SuperNode visitNew(langParser.NewContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Type type = (Type) e.type().accept(this);
        Exp expression = null;
        if(e.exp()!= null) {
            expression = (Exp) e.exp().accept(this);
        }
        New nodeNew = new New(line, column, type, expression);
        return nodeNew;
	}

	@Override 
	public SuperNode visitCallExp(langParser.CallExpContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Exp expression = (Exp) e.exp().accept(this);
        Exp[] params = null;
        SuperNode result = this.defaultResult();
        if(e.exps()!= null) {
            int n = e.exps().exp().size();
            if (n != 0) {
                params = new Exp[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(e, result); ++i) {
                    ParseTree c = e.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Exp) this.aggregateResult(result, childResult);
                }
            }
        }
        CallExp nodeCallExp = null;
        if (e.ID().getText() != null) {
            nodeCallExp = new CallExp(line,column,e.ID().getText(), params, expression);
        }
        else {
            nodeCallExp = new CallExp(line,column,e.IDTYPE().getText(), params, expression);
        }
        return nodeCallExp; 
	}

	@Override 
	public SuperNode visitVarIds(langParser.VarIdsContext e) { 
        int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Var nodeVar = null;
        if (e.ID().getText() != null) {
            nodeVar = new Var(line, column, e.ID().getText());
        } else {
            nodeVar = new Var(line, column, e.IDTYPE().getText());
        }
        return nodeVar; 
	}

	@Override 
	public SuperNode visitAccessData(langParser.AccessDataContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Var nodeAccessData = (Var) e.var().accept(this);
        if (e.ID().getText() != null) {
            nodeAccessData.add(new AccessData(line, column, e.ID().getText()));
        } else {
            nodeAccessData.add(new AccessData(line, column, e.IDTYPE().getText()));
        }
        return nodeAccessData; 
	}

	@Override 
	public SuperNode visitLExp(langParser.LExpContext e) { 
		int line = e.getStart().getLine();
        int column = e.getStart().getCharPositionInLine();
        Var nodeLExp = (Var) e.var().accept(this);
        Exp expression = (Exp) e.exp().accept(this);
        nodeLExp.add(new LExp(line, column, expression));
        return nodeLExp; 
	}

	@Override public SuperNode visitExps(langParser.ExpsContext e) { return visitChildren(e); }
}
