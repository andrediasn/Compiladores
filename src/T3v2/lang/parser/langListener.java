// Generated from parser/lang.g4 by ANTLR 4.10.1

    package lang.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link langParser}.
 */
public interface langListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link langParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(langParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(langParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(langParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(langParser.DataContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(langParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(langParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(langParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(langParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(langParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(langParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(langParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(langParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(langParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(langParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#brace}.
	 * @param ctx the parse tree
	 */
	void enterBrace(langParser.BraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#brace}.
	 * @param ctx the parse tree
	 */
	void exitBrace(langParser.BraceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tyInt}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterTyInt(langParser.TyIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tyInt}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitTyInt(langParser.TyIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tyChar}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterTyChar(langParser.TyCharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tyChar}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitTyChar(langParser.TyCharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tyBool}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterTyBool(langParser.TyBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tyBool}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitTyBool(langParser.TyBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tyFloat}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterTyFloat(langParser.TyFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tyFloat}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitTyFloat(langParser.TyFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tyID}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterTyID(langParser.TyIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tyID}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitTyID(langParser.TyIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtList}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterStmtList(langParser.StmtListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtList}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitStmtList(langParser.StmtListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterIf(langParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitIf(langParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterIfElse(langParser.IfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitIfElse(langParser.IfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code iterate}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterIterate(langParser.IterateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code iterate}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitIterate(langParser.IterateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code read}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterRead(langParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code read}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitRead(langParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code print}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterPrint(langParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code print}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitPrint(langParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code return}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterReturn(langParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code return}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitReturn(langParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attr}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterAttr(langParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitAttr(langParser.AttrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callCMD}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCallCMD(langParser.CallCMDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callCMD}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCallCMD(langParser.CallCMDContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#exps}.
	 * @param ctx the parse tree
	 */
	void enterExps(langParser.ExpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#exps}.
	 * @param ctx the parse tree
	 */
	void exitExps(langParser.ExpsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rex}
	 * labeled alternative in {@link langParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterRex(langParser.RexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rex}
	 * labeled alternative in {@link langParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitRex(langParser.RexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and}
	 * labeled alternative in {@link langParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAnd(langParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and}
	 * labeled alternative in {@link langParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAnd(langParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aex}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void enterAex(langParser.AexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aex}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void exitAex(langParser.AexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code less}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void enterLess(langParser.LessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code less}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void exitLess(langParser.LessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code neq}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void enterNeq(langParser.NeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code neq}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void exitNeq(langParser.NeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void enterEq(langParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eq}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void exitEq(langParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mex}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void enterMex(langParser.MexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mex}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void exitMex(langParser.MexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minus}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void enterMinus(langParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minus}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void exitMinus(langParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plus}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void enterPlus(langParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plus}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void exitPlus(langParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code div}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void enterDiv(langParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code div}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void exitDiv(langParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mult}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void enterMult(langParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void exitMult(langParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mod}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void enterMod(langParser.ModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mod}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void exitMod(langParser.ModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sex}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void enterSex(langParser.SexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sex}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void exitSex(langParser.SexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterNot(langParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitNot(langParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SMinus}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterSMinus(langParser.SMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SMinus}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitSMinus(langParser.SMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code true}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterTrue(langParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code true}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitTrue(langParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code false}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterFalse(langParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code false}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitFalse(langParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code null}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterNull(langParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code null}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitNull(langParser.NullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterInt(langParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitInt(langParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterFloat(langParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitFloat(langParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caracter}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterCaracter(langParser.CaracterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caracter}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitCaracter(langParser.CaracterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pex}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterPex(langParser.PexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pex}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitPex(langParser.PexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vars}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void enterVars(langParser.VarsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vars}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void exitVars(langParser.VarsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expression}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void enterExpression(langParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void exitExpression(langParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code new}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void enterNew(langParser.NewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code new}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void exitNew(langParser.NewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExp}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void enterCallExp(langParser.CallExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExp}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void exitCallExp(langParser.CallExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varIds}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVarIds(langParser.VarIdsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varIds}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVarIds(langParser.VarIdsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code accessData}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 */
	void enterAccessData(langParser.AccessDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code accessData}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 */
	void exitAccessData(langParser.AccessDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code accessArray}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 */
	void enterAccessArray(langParser.AccessArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code accessArray}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 */
	void exitAccessArray(langParser.AccessArrayContext ctx);
}