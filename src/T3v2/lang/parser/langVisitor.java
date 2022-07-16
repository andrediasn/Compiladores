// Generated from parser/lang.g4 by ANTLR 4.10.1

    package lang.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link langParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface langVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link langParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(langParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(langParser.DataContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(langParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(langParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(langParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(langParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(langParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#brace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace(langParser.BraceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tyInt}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyInt(langParser.TyIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tyChar}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyChar(langParser.TyCharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tyBool}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyBool(langParser.TyBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tyFloat}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyFloat(langParser.TyFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tyID}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyID(langParser.TyIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtList}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtList(langParser.StmtListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(langParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(langParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code iterate}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterate(langParser.IterateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(langParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(langParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(langParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(langParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callCMD}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallCMD(langParser.CallCMDContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#exps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExps(langParser.ExpsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rex}
	 * labeled alternative in {@link langParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRex(langParser.RexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link langParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(langParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aex}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAex(langParser.AexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code less}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLess(langParser.LessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code neq}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeq(langParser.NeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(langParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mex}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMex(langParser.MexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minus}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(langParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plus}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(langParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(langParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(langParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mod}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMod(langParser.ModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sex}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSex(langParser.SexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(langParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SMinus}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSMinus(langParser.SMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code true}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(langParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code false}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(langParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code null}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(langParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(langParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code float}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(langParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caracter}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaracter(langParser.CaracterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pex}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPex(langParser.PexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vars}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVars(langParser.VarsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(langParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code new}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNew(langParser.NewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExp}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExp(langParser.CallExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varIds}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarIds(langParser.VarIdsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code accessData}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessData(langParser.AccessDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code accessArray}
	 * labeled alternative in {@link langParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessArray(langParser.AccessArrayContext ctx);
}