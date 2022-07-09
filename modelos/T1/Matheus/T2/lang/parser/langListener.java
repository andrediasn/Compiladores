// Generated from ./parser/lang.g4 by ANTLR 4.9.2

    package lang.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link langParser}.
 */
public interface langListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link langParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(langParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(langParser.ProgContext ctx);
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
	 * Enter a parse tree produced by {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void enterBtype(langParser.BtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#btype}.
	 * @param ctx the parse tree
	 */
	void exitBtype(langParser.BtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(langParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(langParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(langParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(langParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void enterRexp(langParser.RexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#rexp}.
	 * @param ctx the parse tree
	 */
	void exitRexp(langParser.RexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void enterAexp(langParser.AexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#aexp}.
	 * @param ctx the parse tree
	 */
	void exitAexp(langParser.AexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void enterMexp(langParser.MexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#mexp}.
	 * @param ctx the parse tree
	 */
	void exitMexp(langParser.MexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterSexp(langParser.SexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitSexp(langParser.SexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void enterPexp(langParser.PexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#pexp}.
	 * @param ctx the parse tree
	 */
	void exitPexp(langParser.PexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link langParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLvalue(langParser.LvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link langParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLvalue(langParser.LvalueContext ctx);
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
}