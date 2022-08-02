// Generated from lang.g4 by ANTLR 4.8

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
	 * Visit a parse tree produced by {@link langParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(langParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(langParser.DataContext ctx);
	/**oi
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
	 * Visit a parse tree produced by {@link langParser#bracks}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracks(langParser.BracksContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(langParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charType}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharType(langParser.CharTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(langParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatType}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatType(langParser.FloatTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idType}
	 * labeled alternative in {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdType(langParser.IdTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdList}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdList(langParser.CmdListContext ctx);
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
	 * Visit a parse tree produced by the {@code atribuir}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtribuir(langParser.AtribuirContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callCmd}
	 * labeled alternative in {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallCmd(langParser.CallCmdContext ctx);
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
	 * Visit a parse tree produced by the {@code equal}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(langParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aex}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAex(langParser.AexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(langParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notEqual}
	 * labeled alternative in {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqual(langParser.NotEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(langParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mex}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMex(langParser.MexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link langParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(langParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(langParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mod}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMod(langParser.ModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(langParser.MulContext ctx);
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
	 * Visit a parse tree produced by the {@code neg}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeg(langParser.NegContext ctx);
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
	 * Visit a parse tree produced by the {@code char}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar(langParser.CharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pex}
	 * labeled alternative in {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPex(langParser.PexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lval}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLval(langParser.LvalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ex}
	 * labeled alternative in {@link langParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEx(langParser.ExContext ctx);
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
	 * Visit a parse tree produced by the {@code seletorData}
	 * labeled alternative in {@link langParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeletorData(langParser.SeletorDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lvalueID}
	 * labeled alternative in {@link langParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalueID(langParser.LvalueIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seletorArray}
	 * labeled alternative in {@link langParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeletorArray(langParser.SeletorArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#exps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExps(langParser.ExpsContext ctx);
}