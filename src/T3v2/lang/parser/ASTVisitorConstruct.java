/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.parser;

import lang.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class ASTVisitorConstruct extends langBaseVisitor<SuperNode> {

    @Override 
	public SuperNode visitProgram(langParser.ProgramContext ctx) {
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Data[] datas = null;
        Func[] funcs = null;
        Program nodeProgram;
        SuperNode result = this.defaultResult();
        int n = ctx.data().size();
        if (n != 0) {
            datas = new Data[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.data(i);
                SuperNode childResult = c.accept(this);
                datas[i] = (Data) this.aggregateResult(result, childResult);
            }
        }
        result = this.defaultResult();
        n = ctx.func().size();
        if (n != 0) {
            funcs = new Func[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.func(i);
                SuperNode childResult = c.accept(this);
                funcs[i] = (Func) this.aggregateResult(result, childResult);
            }
        }
        nodeProgram = new Program(line,column,datas, funcs);
        return nodeProgram; 
    }

	@Override 
	public SuperNode visitData(langParser.DataContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Decl[] declaration = null;
        SuperNode result = this.defaultResult();
        int n = ctx.decl().size();
        if (n != 0) {
            declaration = new Decl[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.decl(i);
                SuperNode childResult = c.accept(this);
                declaration[i] = (Decl) this.aggregateResult(result, childResult);
            }
        }
        Data nodeData = new Data(line,column, ctx.IDTYPE().getText(), declaration);
        return nodeData;
	}


	@Override 
	public SuperNode visitDecl(langParser.DeclContext ctx)  { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Type type = (Type) ctx.type().accept(this);
        Decl nodeDecl = null;
        if (ctx.ID().getText() != null) {
            nodeDecl = new Decl(line, column,ctx.ID().getText(), type);
        } else {
            nodeDecl = new Decl(line,column,ctx.IDTYPE().getText(), type);
        }
        return nodeDecl; 
	}


	@Override 
	public SuperNode visitFunc(langParser.FuncContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Params[] params = null;
        Type[] returnable = null;
        Cmd[] comand = null;
        SuperNode result = this.defaultResult();
        if(ctx.params()!= null) {
            int n = ctx.params().param().size();
            if (n != 0) {
                params = new Params[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.params().param(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Params) this.aggregateResult(result, childResult);
                }
            }
        }
        result = this.defaultResult();
        int n = ctx.type().size();
        if (n != 0) {
            returnable = new Type[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.type(i);
                SuperNode childResult = c.accept(this);
                returnable[i] = (Type) this.aggregateResult(result, childResult);
            }
        }
        result = this.defaultResult();
        n = ctx.cmd().size();
        if (n != 0) {
            comand = new Cmd[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.cmd(i);
                SuperNode childResult = c.accept(this);
                comand[i] = (Cmd) this.aggregateResult(result, childResult);
            }
        }
        Func nodeFunc = null;
        if (ctx.ID().getText() != null) {
            nodeFunc = new Func(line, column, ctx.ID().getText(), params, returnable, comand);
        } else {
            nodeFunc = new Func(line,column,ctx.IDTYPE().getText(), params, returnable, comand);
        }
        
        return nodeFunc;
	}

	@Override public SuperNode visitParams(langParser.ParamsContext ctx) { 
        return visitChildren(ctx); 
    }

	@Override 
	public SuperNode visitParam(langParser.ParamContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Type type = (Type) ctx.type().accept(this);
        Params nodeParam = null;
        if (ctx.ID().getText() != null) {
            nodeParam = new Params(line, column, ctx.ID().getText(), type);
        } else {
            nodeParam = new Params(line, column, ctx.IDTYPE().getText(), type);
        }
        return nodeParam;
	}

	@Override 
    public SuperNode visitBrace(langParser.BraceContext ctx) { 
        return visitChildren(ctx); 
    }
	
	@Override 
	public SuperNode visitType(langParser.TypeContext ctx) { 
	 	int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        BType nodeBType = (BType) ctx.btype().accept(this);
        int braces = ctx.brace().size();
        Type nodeType = new Type(line, column, nodeBType, braces);
        return nodeType; 
	}
	
	@Override 
	public SuperNode visitTyInt(langParser.TyIntContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyInt nodeTyInt = new TyInt(line,column);
        return nodeTyInt;
	}
	
	@Override 
	public SuperNode visitTyChar(langParser.TyCharContext ctx) {     
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyChar nodeTyChar = new TyChar(line,column);
        return nodeTyChar;
	}

	@Override 
	public SuperNode visitTyBool(langParser.TyBoolContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyBool nodeTyBool = new TyBool(line,column);
        return nodeTyBool;
	}


	@Override 
	public SuperNode visitTyFloat(langParser.TyFloatContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyFloat nodeTyFloat = new TyFloat(line,column);
        return nodeTyFloat;
	}

	@Override 
	public SuperNode visitTyIDType(langParser.TyIDTypeContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        TyIDType nodeTyID = new TyIDType(line,column,ctx.IDTYPE().getText());
        return nodeTyID; 
	}

	@Override 
	public SuperNode visitCmdArray(langParser.CmdArrayContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Cmd[] cmds = null;
        SuperNode result = this.defaultResult();
        int n = ctx.cmd().size();
        if (n != 0) {
            cmds = new Cmd[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.cmd(i);
                SuperNode childResult = c.accept(this);
                cmds[i] = (Cmd) this.aggregateResult(result, childResult);
            }
        }
        CmdArray nodeCmdArray = new CmdArray(line,column,cmds);
        return nodeCmdArray;
	}
	
	@Override 
	public SuperNode visitIf(langParser.IfContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp expression = (Exp) ctx.exp().accept(this);
        Cmd the = (Cmd) ctx.cmd().accept(this);
        If nodeIf = new If(line, column, expression, the);
        return nodeIf;
	}
	
	@Override 
	public SuperNode visitIfElse(langParser.IfElseContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp expression = (Exp) ctx.exp().accept(this);
        Cmd the = (Cmd) ctx.cmd(0).accept(this);
        Cmd els = (Cmd) ctx.cmd(1).accept(this);
        If nodeIfElse = new If(line, column, expression, the, els);
        return nodeIfElse;
	}
	
	@Override 
	public SuperNode visitIterate(langParser.IterateContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp expression = (Exp) ctx.exp().accept(this);
        Cmd cmd = (Cmd) ctx.cmd().accept(this);
        Iterate nodeIterate = new Iterate(line, column, expression, cmd);
        return nodeIterate;
	}

	@Override 
	public SuperNode visitRead(langParser.ReadContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue value = (Lvalue) ctx.lvalue().accept(this);
        Read nodeRead = new Read(line, column, value);
        return nodeRead;
	}
	
	@Override 
	public SuperNode visitPrint(langParser.PrintContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp expression = (Exp) ctx.exp().accept(this);
        Print nodePrint = new Print(line, column, expression);
        return nodePrint;
	}
		
	@Override 
	public SuperNode visitReturn(langParser.ReturnContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp[] expressions = null;
        SuperNode result = this.defaultResult();
        int n = ctx.exp().size();
        if (n != 0) {
            expressions = new Exp[n];
            for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                ParseTree c = ctx.exp(i);
                SuperNode childResult = c.accept(this);
                expressions[i] = (Exp) this.aggregateResult(result, childResult);
            }
        }
        Return nodeReturn = new Return(line, column, expressions);
        return nodeReturn;
	}
	
	@Override 
	public SuperNode visitAttr(langParser.AttrContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue value = (Lvalue) ctx.lvalue().accept(this);
        Exp expression = (Exp) ctx.exp().accept(this);
        Attr nodeAttr = new Attr(line, column, value, expression);
        return nodeAttr;
	}


	@Override 
	public SuperNode visitCallCMD(langParser.CallCMDContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp[] expressions = null;
        Lvalue[] values = null;
        SuperNode result = this.defaultResult();
        if(ctx.exps()!= null) {
            int n = ctx.exps().exp().size();
            if (n != 0) {
                expressions = new Exp[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    expressions[i] = (Exp) this.aggregateResult(result, childResult);
                }
            }
        }
        result = this.defaultResult();
        if(ctx.lvalue() != null) {
            int n = ctx.lvalue().size();
            if (n != 0) {
                values = new Lvalue[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.lvalue(i);
                    SuperNode childResult = c.accept(this);
                    values[i] = (Lvalue) this.aggregateResult(result, childResult);
                }
            }
        }
        
        CallCmd nodeCall = null;
        if (ctx.ID().getText() != null) {
            nodeCall = new CallCmd(line,column,ctx.ID().getText(), expressions, values);
        }
        else {
            nodeCall = new CallCmd(line,column,ctx.IDTYPE().getText(), expressions, values);
        }
        return nodeCall;
	}
	
	@Override public SuperNode visitRex(langParser.RexContext ctx) { 
        return visitChildren(ctx); 
    }

	@Override 
	public SuperNode visitAnd(langParser.AndContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.exp(0).accept(this);
        Exp right = (Exp) ctx.exp(1).accept(this);
        And nodeAnd = new And(line, column, left, right);
        return nodeAnd;
	}

	@Override 
	public SuperNode visitAex(langParser.AexContext ctx) { 
        return visitChildren(ctx); 
    }
	
	@Override 
	public SuperNode visitLess(langParser.LessContext ctx) { 
		    int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.aexp(0).accept(this);
        Exp right = (Exp) ctx.aexp(1).accept(this);
        Less nodeLess = new Less(line, column, left, right);
        return nodeLess;
	}
	
	@Override 
	public SuperNode visitNeq(langParser.NeqContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.rexp().accept(this);
        Exp right = (Exp) ctx.aexp().accept(this);

        Neq nodeNeq = new Neq(line, column, left, right);
        return nodeNeq; 
	}

	@Override 
	public SuperNode visitEq(langParser.EqContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.rexp().accept(this);
        Exp right = (Exp) ctx.aexp().accept(this);
        Eq nodeEq = new Eq(line, column, left, right);
        return nodeEq;
	}
	
	@Override 
	public SuperNode visitMex(langParser.MexContext ctx) { 
        return visitChildren(ctx); 
    }

	@Override 
	public SuperNode visitMinus(langParser.MinusContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.aexp().accept(this);
        Exp right = (Exp) ctx.mexp().accept(this);
        Minus nodeMinus= new Minus(line, column, left, right);
        return nodeMinus;
	}
	
	@Override 
	public SuperNode visitPlus(langParser.PlusContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.aexp().accept(this);
        Exp right = (Exp) ctx.mexp().accept(this);
        Plus nodePlus = new Plus(line, column, left, right);
        return nodePlus;
	}
	
	@Override 
	public SuperNode visitDiv(langParser.DivContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.mexp().accept(this);
        Exp right = (Exp) ctx.sexp().accept(this);
        Div nodeDiv = new Div(line, column, left, right);
        return nodeDiv; 
	}

	@Override 
	public SuperNode visitMult(langParser.MultContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.mexp().accept(this);
        Exp right = (Exp) ctx.sexp().accept(this);
        Mult nodeMult = new Mult(line, column, left, right);
        return nodeMult;
	}
	
	@Override 
	public SuperNode visitSex(langParser.SexContext ctx) { 
        return visitChildren(ctx); 
    }

	@Override 
	public SuperNode visitModule(langParser.ModuleContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp left = (Exp) ctx.mexp().accept(this);
        Exp right = (Exp) ctx.sexp().accept(this);
        ModuleT nodeModule = new ModuleT(line, column, left, right);
        return nodeModule;
	}
	
	@Override 
	public SuperNode visitNot(langParser.NotContext ctx) { 
	    int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp expression = (Exp) ctx.sexp().accept(this);
        Not nodeNot = new Not(line, column, expression);
        return nodeNot;  
	}
	
	@Override 
	public SuperNode visitNeg(langParser.NegContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp expression = (Exp) ctx.sexp().accept(this);
        Neg nodeNeg= new Neg(line, column, expression);
        return nodeNeg;
	}

	@Override 
	public SuperNode visitTrue(langParser.TrueContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        True nodeTrue = new True(line, column);
        return nodeTrue;
	}

	@Override 
	public SuperNode visitFalse(langParser.FalseContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        False nodeFalse = new False(line, column);
        return nodeFalse;
	}

	@Override 
	public SuperNode visitNull(langParser.NullContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Null nodeNull = new Null(line, column);
        return nodeNull;
	}

	@Override 
	public SuperNode visitInteger(langParser.IntegerContext ctx) { 
		int line = ctx.getStart().getLine();
		int column = ctx.getStart().getCharPositionInLine();
        IntegerT nodeInteger = new IntegerT( line, column, Integer.parseInt(ctx.INTEGER().getText()));
        return nodeInteger; 
	}
	
	@Override 
	public SuperNode visitDouble(langParser.DoubleContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        DoubleT nodeDouble = new DoubleT( line,column,Float.parseFloat(ctx.DOUBLE().getText()));
        return nodeDouble;
	}

	@Override 
	public SuperNode visitCaracter(langParser.CaracterContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        String s = ctx.CARACTER().getText();
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

	@Override public SuperNode visitPex(langParser.PexContext ctx) { 
        return visitChildren(ctx); 
    }

	@Override public SuperNode visitLvalues(langParser.LvaluesContext ctx) { 
        return visitChildren(ctx);
    }
	
	@Override 
	public SuperNode visitExpression(langParser.ExpressionContext ctx) { 
		Exp nodeExpression = (Exp) ctx.exp().accept(this);
        return nodeExpression;
	}

	@Override 
	public SuperNode visitNew(langParser.NewContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Type type = (Type) ctx.type().accept(this);
        Exp expression = null;
        if(ctx.exp()!= null) {
            expression = (Exp) ctx.exp().accept(this);
        }
        New nodeNew = new New(line, column, type, expression);
        return nodeNew;
	}

	@Override 
	public SuperNode visitCallExp(langParser.CallExpContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Exp expression = (Exp) ctx.exp().accept(this);
        Exp[] params = null;
        SuperNode result = this.defaultResult();
        if(ctx.exps()!= null) {
            int n = ctx.exps().exp().size();
            if (n != 0) {
                params = new Exp[n];
                for(int i = 0; i < n && this.shouldVisitNextChild(ctx, result); ++i) {
                    ParseTree c = ctx.exps().exp(i);
                    SuperNode childResult = c.accept(this);
                    params[i] = (Exp) this.aggregateResult(result, childResult);
                }
            }
        }
        CallExp nodeCallExp = null;
        if (ctx.ID().getText() != null) {
            nodeCallExp = new CallExp(line,column,ctx.ID().getText(), params, expression);
        }
        else {
            nodeCallExp = new CallExp(line,column,ctx.IDTYPE().getText(), params, expression);
        }
        return nodeCallExp; 
	}

	@Override 
	public SuperNode visitLvalueIds(langParser.LvalueIdsContext ctx) { 
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue nodeLvalue = null;
        if (ctx.ID().getText() != null) {
            nodeLvalue = new Lvalue(line, column, ctx.ID().getText());
        } else {
            nodeLvalue = new Lvalue(line, column, ctx.IDTYPE().getText());
        }
        return nodeLvalue; 
	}

	@Override 
	public SuperNode visitSelectorData(langParser.SelectorDataContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue nodeSelectorData = (Lvalue) ctx.lvalue().accept(this);
        if (ctx.ID().getText() != null) {
            nodeSelectorData.add(new SelectorData(line, column, ctx.ID().getText()));
        } else {
            nodeSelectorData.add(new SelectorData(line, column, ctx.IDTYPE().getText()));
        }
        return nodeSelectorData; 
	}

	@Override 
	public SuperNode visitSelectorArray(langParser.SelectorArrayContext ctx) { 
		int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        Lvalue nodeSelectorArray = (Lvalue) ctx.lvalue().accept(this);
        Exp expression = (Exp) ctx.exp().accept(this);
        nodeSelectorArray.add(new SelectorArray(line, column, expression));
        return nodeSelectorArray; 
	}

	@Override public SuperNode visitExps(langParser.ExpsContext ctx) { return visitChildren(ctx); }
}

