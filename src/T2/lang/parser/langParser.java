// Generated from parser/lang.g4 by ANTLR 4.10.1

    package lang.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class langParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, FLOAT=2, DATA=3, CHAR=4, BOOL=5, TRUE=6, FALSE=7, NULL=8, LEFTPARENT=9, 
		RIGHTPARENT=10, LEFTBRACE=11, RIGHTBRACE=12, LEFTBRACKET=13, RIGHTBRACKET=14, 
		GREATER=15, LESS=16, DOT=17, COMMA=18, COLON=19, SEMICOLON=20, DOUBLECOLON=21, 
		ASSIGN=22, EQ=23, NEQ=24, PLUS=25, MINUS=26, MULT=27, DIV=28, MODULE=29, 
		AND=30, NOT=31, IF=32, ELSE=33, ITERATE=34, READ=35, PRINT=36, RETURN=37, 
		NEW=38, INTEGER=39, DOUBLE=40, CARACTER=41, FIMDELINHA=42, BRANCOS=43, 
		SINGLELINECOMMENT=44, MULTIPLELINESCOMMENT=45, ID=46, IDTYPE=47;
	public static final int
		RULE_prog = 0, RULE_data = 1, RULE_decl = 2, RULE_func = 3, RULE_params = 4, 
		RULE_type = 5, RULE_btype = 6, RULE_cmd = 7, RULE_exp = 8, RULE_rexp = 9, 
		RULE_aexp = 10, RULE_mexp = 11, RULE_sexp = 12, RULE_pexp = 13, RULE_lvalue = 14, 
		RULE_exps = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "data", "decl", "func", "params", "type", "btype", "cmd", "exp", 
			"rexp", "aexp", "mexp", "sexp", "pexp", "lvalue", "exps"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Int'", "'Float'", "'data'", "'Char'", "'Bool'", "'true'", "'false'", 
			"'null'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'>'", "'<'", "'.'", 
			"','", "':'", "';'", "'::'", "'='", "'=='", "'!='", "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'&&'", "'!'", "'if'", "'else'", "'iterate'", "'read'", 
			"'print'", "'return'", "'new'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "FLOAT", "DATA", "CHAR", "BOOL", "TRUE", "FALSE", "NULL", 
			"LEFTPARENT", "RIGHTPARENT", "LEFTBRACE", "RIGHTBRACE", "LEFTBRACKET", 
			"RIGHTBRACKET", "GREATER", "LESS", "DOT", "COMMA", "COLON", "SEMICOLON", 
			"DOUBLECOLON", "ASSIGN", "EQ", "NEQ", "PLUS", "MINUS", "MULT", "DIV", 
			"MODULE", "AND", "NOT", "IF", "ELSE", "ITERATE", "READ", "PRINT", "RETURN", 
			"NEW", "INTEGER", "DOUBLE", "CARACTER", "FIMDELINHA", "BRANCOS", "SINGLELINECOMMENT", 
			"MULTIPLELINESCOMMENT", "ID", "IDTYPE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public langParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public List<DataContext> data() {
			return getRuleContexts(DataContext.class);
		}
		public DataContext data(int i) {
			return getRuleContext(DataContext.class,i);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DATA) {
				{
				{
				setState(32);
				data();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				func();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID || _la==IDTYPE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(langParser.DATA, 0); }
		public TerminalNode IDTYPE() { return getToken(langParser.IDTYPE, 0); }
		public TerminalNode LEFTBRACKET() { return getToken(langParser.LEFTBRACKET, 0); }
		public TerminalNode RIGHTBRACKET() { return getToken(langParser.RIGHTBRACKET, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitData(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(DATA);
			setState(44);
			match(IDTYPE);
			setState(45);
			match(LEFTBRACKET);
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				decl();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID || _la==IDTYPE );
			setState(51);
			match(RIGHTBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public TerminalNode DOUBLECOLON() { return getToken(langParser.DOUBLECOLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(langParser.SEMICOLON, 0); }
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode IDTYPE() { return getToken(langParser.IDTYPE, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==IDTYPE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(54);
			match(DOUBLECOLON);
			setState(55);
			type(0);
			setState(56);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode LEFTPARENT() { return getToken(langParser.LEFTPARENT, 0); }
		public TerminalNode RIGHTPARENT() { return getToken(langParser.RIGHTPARENT, 0); }
		public TerminalNode LEFTBRACKET() { return getToken(langParser.LEFTBRACKET, 0); }
		public TerminalNode RIGHTBRACKET() { return getToken(langParser.RIGHTBRACKET, 0); }
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode IDTYPE() { return getToken(langParser.IDTYPE, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode COLON() { return getToken(langParser.COLON, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(langParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(langParser.COMMA, i);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitFunc(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==IDTYPE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(59);
			match(LEFTPARENT);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID || _la==IDTYPE) {
				{
				setState(60);
				params();
				}
			}

			setState(63);
			match(RIGHTPARENT);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(64);
				match(COLON);
				setState(65);
				type(0);
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(66);
					match(COMMA);
					setState(67);
					type(0);
					}
					}
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(75);
			match(LEFTBRACKET);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFTBRACKET) | (1L << IF) | (1L << ITERATE) | (1L << READ) | (1L << PRINT) | (1L << RETURN) | (1L << ID) | (1L << IDTYPE))) != 0)) {
				{
				{
				setState(76);
				cmd();
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
			match(RIGHTBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<TerminalNode> DOUBLECOLON() { return getTokens(langParser.DOUBLECOLON); }
		public TerminalNode DOUBLECOLON(int i) {
			return getToken(langParser.DOUBLECOLON, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(langParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(langParser.ID, i);
		}
		public List<TerminalNode> IDTYPE() { return getTokens(langParser.IDTYPE); }
		public TerminalNode IDTYPE(int i) {
			return getToken(langParser.IDTYPE, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(langParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(langParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==IDTYPE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(85);
			match(DOUBLECOLON);
			setState(86);
			type(0);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(87);
				match(COMMA);
				setState(88);
				_la = _input.LA(1);
				if ( !(_la==ID || _la==IDTYPE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(89);
				match(DOUBLECOLON);
				setState(90);
				type(0);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BtypeContext btype() {
			return getRuleContext(BtypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LEFTBRACE() { return getToken(langParser.LEFTBRACE, 0); }
		public TerminalNode RIGHTBRACE() { return getToken(langParser.RIGHTBRACE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(97);
			btype();
			}
			_ctx.stop = _input.LT(-1);
			setState(104);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(99);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(100);
					match(LEFTBRACE);
					setState(101);
					match(RIGHTBRACE);
					}
					} 
				}
				setState(106);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BtypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(langParser.INT, 0); }
		public TerminalNode CHAR() { return getToken(langParser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(langParser.BOOL, 0); }
		public TerminalNode FLOAT() { return getToken(langParser.FLOAT, 0); }
		public TerminalNode IDTYPE() { return getToken(langParser.IDTYPE, 0); }
		public BtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_btype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterBtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitBtype(this);
		}
	}

	public final BtypeContext btype() throws RecognitionException {
		BtypeContext _localctx = new BtypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_btype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << BOOL) | (1L << IDTYPE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public TerminalNode LEFTBRACKET() { return getToken(langParser.LEFTBRACKET, 0); }
		public TerminalNode RIGHTBRACKET() { return getToken(langParser.RIGHTBRACKET, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode IF() { return getToken(langParser.IF, 0); }
		public TerminalNode LEFTPARENT() { return getToken(langParser.LEFTPARENT, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode RIGHTPARENT() { return getToken(langParser.RIGHTPARENT, 0); }
		public TerminalNode ELSE() { return getToken(langParser.ELSE, 0); }
		public TerminalNode ITERATE() { return getToken(langParser.ITERATE, 0); }
		public TerminalNode READ() { return getToken(langParser.READ, 0); }
		public List<LvalueContext> lvalue() {
			return getRuleContexts(LvalueContext.class);
		}
		public LvalueContext lvalue(int i) {
			return getRuleContext(LvalueContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(langParser.SEMICOLON, 0); }
		public TerminalNode PRINT() { return getToken(langParser.PRINT, 0); }
		public TerminalNode RETURN() { return getToken(langParser.RETURN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(langParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(langParser.COMMA, i);
		}
		public TerminalNode ASSIGN() { return getToken(langParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode IDTYPE() { return getToken(langParser.IDTYPE, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public TerminalNode LESS() { return getToken(langParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(langParser.GREATER, 0); }
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmd);
		int _la;
		try {
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				match(LEFTBRACKET);
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFTBRACKET) | (1L << IF) | (1L << ITERATE) | (1L << READ) | (1L << PRINT) | (1L << RETURN) | (1L << ID) | (1L << IDTYPE))) != 0)) {
					{
					{
					setState(110);
					cmd();
					}
					}
					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(116);
				match(RIGHTBRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(IF);
				setState(118);
				match(LEFTPARENT);
				setState(119);
				exp(0);
				setState(120);
				match(RIGHTPARENT);
				setState(121);
				cmd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				match(IF);
				setState(124);
				match(LEFTPARENT);
				setState(125);
				exp(0);
				setState(126);
				match(RIGHTPARENT);
				setState(127);
				cmd();
				setState(128);
				match(ELSE);
				setState(129);
				cmd();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(131);
				match(ITERATE);
				setState(132);
				match(LEFTPARENT);
				setState(133);
				exp(0);
				setState(134);
				match(RIGHTPARENT);
				setState(135);
				cmd();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(137);
				match(READ);
				setState(138);
				lvalue(0);
				setState(139);
				match(SEMICOLON);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				match(PRINT);
				setState(142);
				exp(0);
				setState(143);
				match(SEMICOLON);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(145);
				match(RETURN);
				setState(146);
				exp(0);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(147);
					match(COMMA);
					setState(148);
					exp(0);
					}
					}
					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(154);
				match(SEMICOLON);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(156);
				lvalue(0);
				setState(157);
				match(ASSIGN);
				setState(158);
				exp(0);
				setState(159);
				match(SEMICOLON);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(161);
				_la = _input.LA(1);
				if ( !(_la==ID || _la==IDTYPE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(162);
				match(LEFTPARENT);
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << LEFTPARENT) | (1L << MINUS) | (1L << NOT) | (1L << NEW) | (1L << INTEGER) | (1L << DOUBLE) | (1L << CARACTER) | (1L << ID) | (1L << IDTYPE))) != 0)) {
					{
					setState(163);
					exps();
					}
				}

				setState(166);
				match(RIGHTPARENT);
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(167);
					match(LESS);
					setState(168);
					lvalue(0);
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(169);
						match(COMMA);
						setState(170);
						lvalue(0);
						}
						}
						setState(175);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(176);
					match(GREATER);
					}
				}

				setState(180);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public RexpContext rexp() {
			return getRuleContext(RexpContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode AND() { return getToken(langParser.AND, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(184);
			rexp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(191);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(186);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(187);
					match(AND);
					setState(188);
					exp(3);
					}
					} 
				}
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RexpContext extends ParserRuleContext {
		public List<AexpContext> aexp() {
			return getRuleContexts(AexpContext.class);
		}
		public AexpContext aexp(int i) {
			return getRuleContext(AexpContext.class,i);
		}
		public TerminalNode LESS() { return getToken(langParser.LESS, 0); }
		public RexpContext rexp() {
			return getRuleContext(RexpContext.class,0);
		}
		public TerminalNode EQ() { return getToken(langParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(langParser.NEQ, 0); }
		public RexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterRexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitRexp(this);
		}
	}

	public final RexpContext rexp() throws RecognitionException {
		return rexp(0);
	}

	private RexpContext rexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RexpContext _localctx = new RexpContext(_ctx, _parentState);
		RexpContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_rexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(195);
				aexp(0);
				setState(196);
				match(LESS);
				setState(197);
				aexp(0);
				}
				break;
			case 2:
				{
				setState(199);
				aexp(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(210);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(208);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new RexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rexp);
						setState(202);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(203);
						match(EQ);
						setState(204);
						aexp(0);
						}
						break;
					case 2:
						{
						_localctx = new RexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rexp);
						setState(205);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(206);
						match(NEQ);
						setState(207);
						aexp(0);
						}
						break;
					}
					} 
				}
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AexpContext extends ParserRuleContext {
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(langParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(langParser.MINUS, 0); }
		public AexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterAexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitAexp(this);
		}
	}

	public final AexpContext aexp() throws RecognitionException {
		return aexp(0);
	}

	private AexpContext aexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AexpContext _localctx = new AexpContext(_ctx, _parentState);
		AexpContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_aexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(214);
			mexp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(224);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(222);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new AexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_aexp);
						setState(216);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(217);
						match(PLUS);
						setState(218);
						mexp(0);
						}
						break;
					case 2:
						{
						_localctx = new AexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_aexp);
						setState(219);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(220);
						match(MINUS);
						setState(221);
						mexp(0);
						}
						break;
					}
					} 
				}
				setState(226);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MexpContext extends ParserRuleContext {
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public MexpContext mexp() {
			return getRuleContext(MexpContext.class,0);
		}
		public TerminalNode MULT() { return getToken(langParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(langParser.DIV, 0); }
		public TerminalNode MODULE() { return getToken(langParser.MODULE, 0); }
		public MexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterMexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitMexp(this);
		}
	}

	public final MexpContext mexp() throws RecognitionException {
		return mexp(0);
	}

	private MexpContext mexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MexpContext _localctx = new MexpContext(_ctx, _parentState);
		MexpContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_mexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(228);
			sexp();
			}
			_ctx.stop = _input.LT(-1);
			setState(241);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(239);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new MexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(230);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(231);
						match(MULT);
						setState(232);
						sexp();
						}
						break;
					case 2:
						{
						_localctx = new MexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(233);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(234);
						match(DIV);
						setState(235);
						sexp();
						}
						break;
					case 3:
						{
						_localctx = new MexpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mexp);
						setState(236);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(237);
						match(MODULE);
						setState(238);
						sexp();
						}
						break;
					}
					} 
				}
				setState(243);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SexpContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(langParser.NOT, 0); }
		public SexpContext sexp() {
			return getRuleContext(SexpContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(langParser.MINUS, 0); }
		public TerminalNode TRUE() { return getToken(langParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(langParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(langParser.NULL, 0); }
		public TerminalNode INTEGER() { return getToken(langParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(langParser.DOUBLE, 0); }
		public TerminalNode CARACTER() { return getToken(langParser.CARACTER, 0); }
		public PexpContext pexp() {
			return getRuleContext(PexpContext.class,0);
		}
		public SexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterSexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitSexp(this);
		}
	}

	public final SexpContext sexp() throws RecognitionException {
		SexpContext _localctx = new SexpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sexp);
		try {
			setState(255);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				match(NOT);
				setState(245);
				sexp();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				match(MINUS);
				setState(247);
				sexp();
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(249);
				match(FALSE);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 5);
				{
				setState(250);
				match(NULL);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 6);
				{
				setState(251);
				match(INTEGER);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 7);
				{
				setState(252);
				match(DOUBLE);
				}
				break;
			case CARACTER:
				enterOuterAlt(_localctx, 8);
				{
				setState(253);
				match(CARACTER);
				}
				break;
			case LEFTPARENT:
			case NEW:
			case ID:
			case IDTYPE:
				enterOuterAlt(_localctx, 9);
				{
				setState(254);
				pexp();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PexpContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode LEFTPARENT() { return getToken(langParser.LEFTPARENT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RIGHTPARENT() { return getToken(langParser.RIGHTPARENT, 0); }
		public TerminalNode NEW() { return getToken(langParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LEFTBRACE() { return getToken(langParser.LEFTBRACE, 0); }
		public TerminalNode RIGHTBRACE() { return getToken(langParser.RIGHTBRACE, 0); }
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode IDTYPE() { return getToken(langParser.IDTYPE, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public PexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterPexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitPexp(this);
		}
	}

	public final PexpContext pexp() throws RecognitionException {
		PexpContext _localctx = new PexpContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pexp);
		int _la;
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				lvalue(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
				match(LEFTPARENT);
				setState(259);
				exp(0);
				setState(260);
				match(RIGHTPARENT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(262);
				match(NEW);
				setState(263);
				type(0);
				setState(268);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(264);
					match(LEFTBRACE);
					setState(265);
					exp(0);
					setState(266);
					match(RIGHTBRACE);
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(270);
				_la = _input.LA(1);
				if ( !(_la==ID || _la==IDTYPE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(271);
				match(LEFTPARENT);
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << LEFTPARENT) | (1L << MINUS) | (1L << NOT) | (1L << NEW) | (1L << INTEGER) | (1L << DOUBLE) | (1L << CARACTER) | (1L << ID) | (1L << IDTYPE))) != 0)) {
					{
					setState(272);
					exps();
					}
				}

				setState(275);
				match(RIGHTPARENT);
				setState(276);
				match(LEFTBRACE);
				setState(277);
				exp(0);
				setState(278);
				match(RIGHTBRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LvalueContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode IDTYPE() { return getToken(langParser.IDTYPE, 0); }
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode LEFTBRACE() { return getToken(langParser.LEFTBRACE, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RIGHTBRACE() { return getToken(langParser.RIGHTBRACE, 0); }
		public TerminalNode DOT() { return getToken(langParser.DOT, 0); }
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitLvalue(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_lvalue, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(283);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==IDTYPE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(293);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(285);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(286);
						match(LEFTBRACE);
						setState(287);
						exp(0);
						setState(288);
						match(RIGHTBRACE);
						}
						break;
					case 2:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(290);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(291);
						match(DOT);
						setState(292);
						_la = _input.LA(1);
						if ( !(_la==ID || _la==IDTYPE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpsContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(langParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(langParser.COMMA, i);
		}
		public ExpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitExps(this);
		}
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			exp(0);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(299);
				match(COMMA);
				setState(300);
				exp(0);
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 8:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 9:
			return rexp_sempred((RexpContext)_localctx, predIndex);
		case 10:
			return aexp_sempred((AexpContext)_localctx, predIndex);
		case 11:
			return mexp_sempred((MexpContext)_localctx, predIndex);
		case 14:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean rexp_sempred(RexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean aexp_sempred(AexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean mexp_sempred(MexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u0133\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0005\u0000\"\b\u0000\n\u0000\f\u0000%\t\u0000\u0001\u0000"+
		"\u0004\u0000(\b\u0000\u000b\u0000\f\u0000)\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0004\u00010\b\u0001\u000b\u0001\f\u00011\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003>\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003E\b\u0003"+
		"\n\u0003\f\u0003H\t\u0003\u0003\u0003J\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0005\u0003N\b\u0003\n\u0003\f\u0003Q\t\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004\\\b\u0004\n\u0004\f\u0004_\t\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"+
		"g\b\u0005\n\u0005\f\u0005j\t\u0005\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0005\u0007p\b\u0007\n\u0007\f\u0007s\t\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007\u0096\b\u0007\n\u0007\f\u0007\u0099"+
		"\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a5"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u00ac\b\u0007\n\u0007\f\u0007\u00af\t\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u00b3\b\u0007\u0001\u0007\u0003\u0007\u00b6\b\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00be\b\b\n\b\f\b\u00c1"+
		"\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00c9\b\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00d1\b\t\n\t"+
		"\f\t\u00d4\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0005\n\u00df\b\n\n\n\f\n\u00e2\t\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00f0"+
		"\b\u000b\n\u000b\f\u000b\u00f3\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0100"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u010d\b\r\u0001\r\u0001\r\u0001\r\u0003\r\u0112"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0119\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0126\b\u000e"+
		"\n\u000e\f\u000e\u0129\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u012e\b\u000f\n\u000f\f\u000f\u0131\t\u000f\u0001\u000f\u0000\u0006"+
		"\n\u0010\u0012\u0014\u0016\u001c\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e\u0000\u0002\u0001\u0000"+
		"./\u0003\u0000\u0001\u0002\u0004\u0005//\u0151\u0000#\u0001\u0000\u0000"+
		"\u0000\u0002+\u0001\u0000\u0000\u0000\u00045\u0001\u0000\u0000\u0000\u0006"+
		":\u0001\u0000\u0000\u0000\bT\u0001\u0000\u0000\u0000\n`\u0001\u0000\u0000"+
		"\u0000\fk\u0001\u0000\u0000\u0000\u000e\u00b5\u0001\u0000\u0000\u0000"+
		"\u0010\u00b7\u0001\u0000\u0000\u0000\u0012\u00c8\u0001\u0000\u0000\u0000"+
		"\u0014\u00d5\u0001\u0000\u0000\u0000\u0016\u00e3\u0001\u0000\u0000\u0000"+
		"\u0018\u00ff\u0001\u0000\u0000\u0000\u001a\u0118\u0001\u0000\u0000\u0000"+
		"\u001c\u011a\u0001\u0000\u0000\u0000\u001e\u012a\u0001\u0000\u0000\u0000"+
		" \"\u0003\u0002\u0001\u0000! \u0001\u0000\u0000\u0000\"%\u0001\u0000\u0000"+
		"\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\'\u0001\u0000"+
		"\u0000\u0000%#\u0001\u0000\u0000\u0000&(\u0003\u0006\u0003\u0000\'&\u0001"+
		"\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000"+
		")*\u0001\u0000\u0000\u0000*\u0001\u0001\u0000\u0000\u0000+,\u0005\u0003"+
		"\u0000\u0000,-\u0005/\u0000\u0000-/\u0005\r\u0000\u0000.0\u0003\u0004"+
		"\u0002\u0000/.\u0001\u0000\u0000\u000001\u0001\u0000\u0000\u00001/\u0001"+
		"\u0000\u0000\u000012\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u0000"+
		"34\u0005\u000e\u0000\u00004\u0003\u0001\u0000\u0000\u000056\u0007\u0000"+
		"\u0000\u000067\u0005\u0015\u0000\u000078\u0003\n\u0005\u000089\u0005\u0014"+
		"\u0000\u00009\u0005\u0001\u0000\u0000\u0000:;\u0007\u0000\u0000\u0000"+
		";=\u0005\t\u0000\u0000<>\u0003\b\u0004\u0000=<\u0001\u0000\u0000\u0000"+
		"=>\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?I\u0005\n\u0000\u0000"+
		"@A\u0005\u0013\u0000\u0000AF\u0003\n\u0005\u0000BC\u0005\u0012\u0000\u0000"+
		"CE\u0003\n\u0005\u0000DB\u0001\u0000\u0000\u0000EH\u0001\u0000\u0000\u0000"+
		"FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000"+
		"\u0000HF\u0001\u0000\u0000\u0000I@\u0001\u0000\u0000\u0000IJ\u0001\u0000"+
		"\u0000\u0000JK\u0001\u0000\u0000\u0000KO\u0005\r\u0000\u0000LN\u0003\u000e"+
		"\u0007\u0000ML\u0001\u0000\u0000\u0000NQ\u0001\u0000\u0000\u0000OM\u0001"+
		"\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PR\u0001\u0000\u0000\u0000"+
		"QO\u0001\u0000\u0000\u0000RS\u0005\u000e\u0000\u0000S\u0007\u0001\u0000"+
		"\u0000\u0000TU\u0007\u0000\u0000\u0000UV\u0005\u0015\u0000\u0000V]\u0003"+
		"\n\u0005\u0000WX\u0005\u0012\u0000\u0000XY\u0007\u0000\u0000\u0000YZ\u0005"+
		"\u0015\u0000\u0000Z\\\u0003\n\u0005\u0000[W\u0001\u0000\u0000\u0000\\"+
		"_\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000"+
		"\u0000^\t\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000`a\u0006\u0005"+
		"\uffff\uffff\u0000ab\u0003\f\u0006\u0000bh\u0001\u0000\u0000\u0000cd\n"+
		"\u0002\u0000\u0000de\u0005\u000b\u0000\u0000eg\u0005\f\u0000\u0000fc\u0001"+
		"\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000"+
		"hi\u0001\u0000\u0000\u0000i\u000b\u0001\u0000\u0000\u0000jh\u0001\u0000"+
		"\u0000\u0000kl\u0007\u0001\u0000\u0000l\r\u0001\u0000\u0000\u0000mq\u0005"+
		"\r\u0000\u0000np\u0003\u000e\u0007\u0000on\u0001\u0000\u0000\u0000ps\u0001"+
		"\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"rt\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000t\u00b6\u0005\u000e"+
		"\u0000\u0000uv\u0005 \u0000\u0000vw\u0005\t\u0000\u0000wx\u0003\u0010"+
		"\b\u0000xy\u0005\n\u0000\u0000yz\u0003\u000e\u0007\u0000z\u00b6\u0001"+
		"\u0000\u0000\u0000{|\u0005 \u0000\u0000|}\u0005\t\u0000\u0000}~\u0003"+
		"\u0010\b\u0000~\u007f\u0005\n\u0000\u0000\u007f\u0080\u0003\u000e\u0007"+
		"\u0000\u0080\u0081\u0005!\u0000\u0000\u0081\u0082\u0003\u000e\u0007\u0000"+
		"\u0082\u00b6\u0001\u0000\u0000\u0000\u0083\u0084\u0005\"\u0000\u0000\u0084"+
		"\u0085\u0005\t\u0000\u0000\u0085\u0086\u0003\u0010\b\u0000\u0086\u0087"+
		"\u0005\n\u0000\u0000\u0087\u0088\u0003\u000e\u0007\u0000\u0088\u00b6\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0005#\u0000\u0000\u008a\u008b\u0003\u001c"+
		"\u000e\u0000\u008b\u008c\u0005\u0014\u0000\u0000\u008c\u00b6\u0001\u0000"+
		"\u0000\u0000\u008d\u008e\u0005$\u0000\u0000\u008e\u008f\u0003\u0010\b"+
		"\u0000\u008f\u0090\u0005\u0014\u0000\u0000\u0090\u00b6\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0005%\u0000\u0000\u0092\u0097\u0003\u0010\b\u0000"+
		"\u0093\u0094\u0005\u0012\u0000\u0000\u0094\u0096\u0003\u0010\b\u0000\u0095"+
		"\u0093\u0001\u0000\u0000\u0000\u0096\u0099\u0001\u0000\u0000\u0000\u0097"+
		"\u0095\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098"+
		"\u009a\u0001\u0000\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u009a"+
		"\u009b\u0005\u0014\u0000\u0000\u009b\u00b6\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0003\u001c\u000e\u0000\u009d\u009e\u0005\u0016\u0000\u0000\u009e"+
		"\u009f\u0003\u0010\b\u0000\u009f\u00a0\u0005\u0014\u0000\u0000\u00a0\u00b6"+
		"\u0001\u0000\u0000\u0000\u00a1\u00a2\u0007\u0000\u0000\u0000\u00a2\u00a4"+
		"\u0005\t\u0000\u0000\u00a3\u00a5\u0003\u001e\u000f\u0000\u00a4\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a6\u00b2\u0005\n\u0000\u0000\u00a7\u00a8\u0005\u0010"+
		"\u0000\u0000\u00a8\u00ad\u0003\u001c\u000e\u0000\u00a9\u00aa\u0005\u0012"+
		"\u0000\u0000\u00aa\u00ac\u0003\u001c\u000e\u0000\u00ab\u00a9\u0001\u0000"+
		"\u0000\u0000\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00b0\u0001\u0000"+
		"\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u000f"+
		"\u0000\u0000\u00b1\u00b3\u0001\u0000\u0000\u0000\u00b2\u00a7\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b6\u0005\u0014\u0000\u0000\u00b5m\u0001\u0000\u0000"+
		"\u0000\u00b5u\u0001\u0000\u0000\u0000\u00b5{\u0001\u0000\u0000\u0000\u00b5"+
		"\u0083\u0001\u0000\u0000\u0000\u00b5\u0089\u0001\u0000\u0000\u0000\u00b5"+
		"\u008d\u0001\u0000\u0000\u0000\u00b5\u0091\u0001\u0000\u0000\u0000\u00b5"+
		"\u009c\u0001\u0000\u0000\u0000\u00b5\u00a1\u0001\u0000\u0000\u0000\u00b6"+
		"\u000f\u0001\u0000\u0000\u0000\u00b7\u00b8\u0006\b\uffff\uffff\u0000\u00b8"+
		"\u00b9\u0003\u0012\t\u0000\u00b9\u00bf\u0001\u0000\u0000\u0000\u00ba\u00bb"+
		"\n\u0002\u0000\u0000\u00bb\u00bc\u0005\u001e\u0000\u0000\u00bc\u00be\u0003"+
		"\u0010\b\u0003\u00bd\u00ba\u0001\u0000\u0000\u0000\u00be\u00c1\u0001\u0000"+
		"\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000"+
		"\u0000\u0000\u00c0\u0011\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c3\u0006\t\uffff\uffff\u0000\u00c3\u00c4\u0003\u0014"+
		"\n\u0000\u00c4\u00c5\u0005\u0010\u0000\u0000\u00c5\u00c6\u0003\u0014\n"+
		"\u0000\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7\u00c9\u0003\u0014\n\u0000"+
		"\u00c8\u00c2\u0001\u0000\u0000\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c9\u00d2\u0001\u0000\u0000\u0000\u00ca\u00cb\n\u0003\u0000\u0000\u00cb"+
		"\u00cc\u0005\u0017\u0000\u0000\u00cc\u00d1\u0003\u0014\n\u0000\u00cd\u00ce"+
		"\n\u0002\u0000\u0000\u00ce\u00cf\u0005\u0018\u0000\u0000\u00cf\u00d1\u0003"+
		"\u0014\n\u0000\u00d0\u00ca\u0001\u0000\u0000\u0000\u00d0\u00cd\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d4\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u0013\u0001\u0000"+
		"\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u00d6\u0006\n\uffff"+
		"\uffff\u0000\u00d6\u00d7\u0003\u0016\u000b\u0000\u00d7\u00e0\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d9\n\u0003\u0000\u0000\u00d9\u00da\u0005\u0019\u0000"+
		"\u0000\u00da\u00df\u0003\u0016\u000b\u0000\u00db\u00dc\n\u0002\u0000\u0000"+
		"\u00dc\u00dd\u0005\u001a\u0000\u0000\u00dd\u00df\u0003\u0016\u000b\u0000"+
		"\u00de\u00d8\u0001\u0000\u0000\u0000\u00de\u00db\u0001\u0000\u0000\u0000"+
		"\u00df\u00e2\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u0015\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3\u00e4\u0006\u000b\uffff\uffff"+
		"\u0000\u00e4\u00e5\u0003\u0018\f\u0000\u00e5\u00f1\u0001\u0000\u0000\u0000"+
		"\u00e6\u00e7\n\u0004\u0000\u0000\u00e7\u00e8\u0005\u001b\u0000\u0000\u00e8"+
		"\u00f0\u0003\u0018\f\u0000\u00e9\u00ea\n\u0003\u0000\u0000\u00ea\u00eb"+
		"\u0005\u001c\u0000\u0000\u00eb\u00f0\u0003\u0018\f\u0000\u00ec\u00ed\n"+
		"\u0002\u0000\u0000\u00ed\u00ee\u0005\u001d\u0000\u0000\u00ee\u00f0\u0003"+
		"\u0018\f\u0000\u00ef\u00e6\u0001\u0000\u0000\u0000\u00ef\u00e9\u0001\u0000"+
		"\u0000\u0000\u00ef\u00ec\u0001\u0000\u0000\u0000\u00f0\u00f3\u0001\u0000"+
		"\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f2\u0017\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f5\u0005\u001f\u0000\u0000\u00f5\u0100\u0003\u0018"+
		"\f\u0000\u00f6\u00f7\u0005\u001a\u0000\u0000\u00f7\u0100\u0003\u0018\f"+
		"\u0000\u00f8\u0100\u0005\u0006\u0000\u0000\u00f9\u0100\u0005\u0007\u0000"+
		"\u0000\u00fa\u0100\u0005\b\u0000\u0000\u00fb\u0100\u0005\'\u0000\u0000"+
		"\u00fc\u0100\u0005(\u0000\u0000\u00fd\u0100\u0005)\u0000\u0000\u00fe\u0100"+
		"\u0003\u001a\r\u0000\u00ff\u00f4\u0001\u0000\u0000\u0000\u00ff\u00f6\u0001"+
		"\u0000\u0000\u0000\u00ff\u00f8\u0001\u0000\u0000\u0000\u00ff\u00f9\u0001"+
		"\u0000\u0000\u0000\u00ff\u00fa\u0001\u0000\u0000\u0000\u00ff\u00fb\u0001"+
		"\u0000\u0000\u0000\u00ff\u00fc\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001"+
		"\u0000\u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u0100\u0019\u0001"+
		"\u0000\u0000\u0000\u0101\u0119\u0003\u001c\u000e\u0000\u0102\u0103\u0005"+
		"\t\u0000\u0000\u0103\u0104\u0003\u0010\b\u0000\u0104\u0105\u0005\n\u0000"+
		"\u0000\u0105\u0119\u0001\u0000\u0000\u0000\u0106\u0107\u0005&\u0000\u0000"+
		"\u0107\u010c\u0003\n\u0005\u0000\u0108\u0109\u0005\u000b\u0000\u0000\u0109"+
		"\u010a\u0003\u0010\b\u0000\u010a\u010b\u0005\f\u0000\u0000\u010b\u010d"+
		"\u0001\u0000\u0000\u0000\u010c\u0108\u0001\u0000\u0000\u0000\u010c\u010d"+
		"\u0001\u0000\u0000\u0000\u010d\u0119\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0007\u0000\u0000\u0000\u010f\u0111\u0005\t\u0000\u0000\u0110\u0112\u0003"+
		"\u001e\u000f\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001"+
		"\u0000\u0000\u0000\u0112\u0113\u0001\u0000\u0000\u0000\u0113\u0114\u0005"+
		"\n\u0000\u0000\u0114\u0115\u0005\u000b\u0000\u0000\u0115\u0116\u0003\u0010"+
		"\b\u0000\u0116\u0117\u0005\f\u0000\u0000\u0117\u0119\u0001\u0000\u0000"+
		"\u0000\u0118\u0101\u0001\u0000\u0000\u0000\u0118\u0102\u0001\u0000\u0000"+
		"\u0000\u0118\u0106\u0001\u0000\u0000\u0000\u0118\u010e\u0001\u0000\u0000"+
		"\u0000\u0119\u001b\u0001\u0000\u0000\u0000\u011a\u011b\u0006\u000e\uffff"+
		"\uffff\u0000\u011b\u011c\u0007\u0000\u0000\u0000\u011c\u0127\u0001\u0000"+
		"\u0000\u0000\u011d\u011e\n\u0002\u0000\u0000\u011e\u011f\u0005\u000b\u0000"+
		"\u0000\u011f\u0120\u0003\u0010\b\u0000\u0120\u0121\u0005\f\u0000\u0000"+
		"\u0121\u0126\u0001\u0000\u0000\u0000\u0122\u0123\n\u0001\u0000\u0000\u0123"+
		"\u0124\u0005\u0011\u0000\u0000\u0124\u0126\u0007\u0000\u0000\u0000\u0125"+
		"\u011d\u0001\u0000\u0000\u0000\u0125\u0122\u0001\u0000\u0000\u0000\u0126"+
		"\u0129\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0127"+
		"\u0128\u0001\u0000\u0000\u0000\u0128\u001d\u0001\u0000\u0000\u0000\u0129"+
		"\u0127\u0001\u0000\u0000\u0000\u012a\u012f\u0003\u0010\b\u0000\u012b\u012c"+
		"\u0005\u0012\u0000\u0000\u012c\u012e\u0003\u0010\b\u0000\u012d\u012b\u0001"+
		"\u0000\u0000\u0000\u012e\u0131\u0001\u0000\u0000\u0000\u012f\u012d\u0001"+
		"\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u001f\u0001"+
		"\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u001e#)1=FIO]h"+
		"q\u0097\u00a4\u00ad\u00b2\u00b5\u00bf\u00c8\u00d0\u00d2\u00de\u00e0\u00ef"+
		"\u00f1\u00ff\u010c\u0111\u0118\u0125\u0127\u012f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}