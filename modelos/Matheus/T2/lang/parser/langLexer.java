// Generated from ./parser/lang.g4 by ANTLR 4.9.2

    package lang.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class langLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"INT", "FLOAT", "DATA", "CHAR", "BOOL", "TRUE", "FALSE", "NULL", "LEFTPARENT", 
			"RIGHTPARENT", "LEFTBRACE", "RIGHTBRACE", "LEFTBRACKET", "RIGHTBRACKET", 
			"GREATER", "LESS", "DOT", "COMMA", "COLON", "SEMICOLON", "DOUBLECOLON", 
			"ASSIGN", "EQ", "NEQ", "PLUS", "MINUS", "MULT", "DIV", "MODULE", "AND", 
			"NOT", "IF", "ELSE", "ITERATE", "READ", "PRINT", "RETURN", "NEW", "INTEGER", 
			"DOUBLE", "CARACTER", "FIMDELINHA", "BRANCOS", "SINGLELINECOMMENT", "MULTIPLELINESCOMMENT", 
			"ID", "IDTYPE"
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


	public langLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u013a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3\37\3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'"+
		"\3\'\3(\6(\u00e4\n(\r(\16(\u00e5\3)\7)\u00e9\n)\f)\16)\u00ec\13)\3)\3"+
		")\6)\u00f0\n)\r)\16)\u00f1\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\5"+
		"*\u0102\n*\3*\3*\3+\5+\u0107\n+\3+\3+\3+\3+\3,\6,\u010e\n,\r,\16,\u010f"+
		"\3,\3,\3-\3-\3-\3-\7-\u0118\n-\f-\16-\u011b\13-\3-\3-\3.\3.\3.\3.\7.\u0123"+
		"\n.\f.\16.\u0126\13.\3.\3.\3.\3.\3.\3/\3/\7/\u012f\n/\f/\16/\u0132\13"+
		"/\3\60\3\60\7\60\u0136\n\60\f\60\16\60\u0139\13\60\3\u0124\2\61\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61\3\2\b\4\2))^^\4\2\13\13\"\"\4\2"+
		"\f\f\17\17\3\2c|\6\2\62;C\\aac|\3\2C\\\2\u0148\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2"+
		"\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2"+
		"\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y"+
		"\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3\2\2\2\5e\3\2\2\2\7k\3\2"+
		"\2\2\tp\3\2\2\2\13u\3\2\2\2\rz\3\2\2\2\17\177\3\2\2\2\21\u0085\3\2\2\2"+
		"\23\u008a\3\2\2\2\25\u008c\3\2\2\2\27\u008e\3\2\2\2\31\u0090\3\2\2\2\33"+
		"\u0092\3\2\2\2\35\u0094\3\2\2\2\37\u0096\3\2\2\2!\u0098\3\2\2\2#\u009a"+
		"\3\2\2\2%\u009c\3\2\2\2\'\u009e\3\2\2\2)\u00a0\3\2\2\2+\u00a2\3\2\2\2"+
		"-\u00a5\3\2\2\2/\u00a7\3\2\2\2\61\u00aa\3\2\2\2\63\u00ad\3\2\2\2\65\u00af"+
		"\3\2\2\2\67\u00b1\3\2\2\29\u00b3\3\2\2\2;\u00b5\3\2\2\2=\u00b7\3\2\2\2"+
		"?\u00ba\3\2\2\2A\u00bc\3\2\2\2C\u00bf\3\2\2\2E\u00c4\3\2\2\2G\u00cc\3"+
		"\2\2\2I\u00d1\3\2\2\2K\u00d7\3\2\2\2M\u00de\3\2\2\2O\u00e3\3\2\2\2Q\u00ea"+
		"\3\2\2\2S\u00f3\3\2\2\2U\u0106\3\2\2\2W\u010d\3\2\2\2Y\u0113\3\2\2\2["+
		"\u011e\3\2\2\2]\u012c\3\2\2\2_\u0133\3\2\2\2ab\7K\2\2bc\7p\2\2cd\7v\2"+
		"\2d\4\3\2\2\2ef\7H\2\2fg\7n\2\2gh\7q\2\2hi\7c\2\2ij\7v\2\2j\6\3\2\2\2"+
		"kl\7f\2\2lm\7c\2\2mn\7v\2\2no\7c\2\2o\b\3\2\2\2pq\7E\2\2qr\7j\2\2rs\7"+
		"c\2\2st\7t\2\2t\n\3\2\2\2uv\7D\2\2vw\7q\2\2wx\7q\2\2xy\7n\2\2y\f\3\2\2"+
		"\2z{\7v\2\2{|\7t\2\2|}\7w\2\2}~\7g\2\2~\16\3\2\2\2\177\u0080\7h\2\2\u0080"+
		"\u0081\7c\2\2\u0081\u0082\7n\2\2\u0082\u0083\7u\2\2\u0083\u0084\7g\2\2"+
		"\u0084\20\3\2\2\2\u0085\u0086\7p\2\2\u0086\u0087\7w\2\2\u0087\u0088\7"+
		"n\2\2\u0088\u0089\7n\2\2\u0089\22\3\2\2\2\u008a\u008b\7*\2\2\u008b\24"+
		"\3\2\2\2\u008c\u008d\7+\2\2\u008d\26\3\2\2\2\u008e\u008f\7]\2\2\u008f"+
		"\30\3\2\2\2\u0090\u0091\7_\2\2\u0091\32\3\2\2\2\u0092\u0093\7}\2\2\u0093"+
		"\34\3\2\2\2\u0094\u0095\7\177\2\2\u0095\36\3\2\2\2\u0096\u0097\7@\2\2"+
		"\u0097 \3\2\2\2\u0098\u0099\7>\2\2\u0099\"\3\2\2\2\u009a\u009b\7\60\2"+
		"\2\u009b$\3\2\2\2\u009c\u009d\7.\2\2\u009d&\3\2\2\2\u009e\u009f\7<\2\2"+
		"\u009f(\3\2\2\2\u00a0\u00a1\7=\2\2\u00a1*\3\2\2\2\u00a2\u00a3\7<\2\2\u00a3"+
		"\u00a4\7<\2\2\u00a4,\3\2\2\2\u00a5\u00a6\7?\2\2\u00a6.\3\2\2\2\u00a7\u00a8"+
		"\7?\2\2\u00a8\u00a9\7?\2\2\u00a9\60\3\2\2\2\u00aa\u00ab\7#\2\2\u00ab\u00ac"+
		"\7?\2\2\u00ac\62\3\2\2\2\u00ad\u00ae\7-\2\2\u00ae\64\3\2\2\2\u00af\u00b0"+
		"\7/\2\2\u00b0\66\3\2\2\2\u00b1\u00b2\7,\2\2\u00b28\3\2\2\2\u00b3\u00b4"+
		"\7\61\2\2\u00b4:\3\2\2\2\u00b5\u00b6\7\'\2\2\u00b6<\3\2\2\2\u00b7\u00b8"+
		"\7(\2\2\u00b8\u00b9\7(\2\2\u00b9>\3\2\2\2\u00ba\u00bb\7#\2\2\u00bb@\3"+
		"\2\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7h\2\2\u00beB\3\2\2\2\u00bf\u00c0"+
		"\7g\2\2\u00c0\u00c1\7n\2\2\u00c1\u00c2\7u\2\2\u00c2\u00c3\7g\2\2\u00c3"+
		"D\3\2\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7v\2\2\u00c6\u00c7\7g\2\2\u00c7"+
		"\u00c8\7t\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7g\2\2"+
		"\u00cbF\3\2\2\2\u00cc\u00cd\7t\2\2\u00cd\u00ce\7g\2\2\u00ce\u00cf\7c\2"+
		"\2\u00cf\u00d0\7f\2\2\u00d0H\3\2\2\2\u00d1\u00d2\7r\2\2\u00d2\u00d3\7"+
		"t\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6\7v\2\2\u00d6J"+
		"\3\2\2\2\u00d7\u00d8\7t\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7v\2\2\u00da"+
		"\u00db\7w\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7p\2\2\u00ddL\3\2\2\2\u00de"+
		"\u00df\7p\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7y\2\2\u00e1N\3\2\2\2\u00e2"+
		"\u00e4\4\62;\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6P\3\2\2\2\u00e7\u00e9\4\62;\2\u00e8\u00e7"+
		"\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb"+
		"\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ef\7\60\2\2\u00ee\u00f0\4"+
		"\62;\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2R\3\2\2\2\u00f3\u0101\7)\2\2\u00f4\u0102\n\2\2\2\u00f5"+
		"\u00f6\7^\2\2\u00f6\u0102\7p\2\2\u00f7\u00f8\7^\2\2\u00f8\u0102\7v\2\2"+
		"\u00f9\u00fa\7^\2\2\u00fa\u0102\7d\2\2\u00fb\u00fc\7^\2\2\u00fc\u0102"+
		"\7t\2\2\u00fd\u00fe\7^\2\2\u00fe\u0102\7^\2\2\u00ff\u0100\7^\2\2\u0100"+
		"\u0102\7)\2\2\u0101\u00f4\3\2\2\2\u0101\u00f5\3\2\2\2\u0101\u00f7\3\2"+
		"\2\2\u0101\u00f9\3\2\2\2\u0101\u00fb\3\2\2\2\u0101\u00fd\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\7)\2\2\u0104T\3\2\2\2\u0105"+
		"\u0107\7\17\2\2\u0106\u0105\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3"+
		"\2\2\2\u0108\u0109\7\f\2\2\u0109\u010a\3\2\2\2\u010a\u010b\b+\2\2\u010b"+
		"V\3\2\2\2\u010c\u010e\t\3\2\2\u010d\u010c\3\2\2\2\u010e\u010f\3\2\2\2"+
		"\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112"+
		"\b,\2\2\u0112X\3\2\2\2\u0113\u0114\7/\2\2\u0114\u0115\7/\2\2\u0115\u0119"+
		"\3\2\2\2\u0116\u0118\n\4\2\2\u0117\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119"+
		"\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0119\3\2"+
		"\2\2\u011c\u011d\b-\3\2\u011dZ\3\2\2\2\u011e\u011f\7}\2\2\u011f\u0120"+
		"\7/\2\2\u0120\u0124\3\2\2\2\u0121\u0123\13\2\2\2\u0122\u0121\3\2\2\2\u0123"+
		"\u0126\3\2\2\2\u0124\u0125\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127\3\2"+
		"\2\2\u0126\u0124\3\2\2\2\u0127\u0128\7/\2\2\u0128\u0129\7\177\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u012b\b.\3\2\u012b\\\3\2\2\2\u012c\u0130\t\5\2\2"+
		"\u012d\u012f\t\6\2\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131^\3\2\2\2\u0132\u0130\3\2\2\2\u0133"+
		"\u0137\t\7\2\2\u0134\u0136\t\6\2\2\u0135\u0134\3\2\2\2\u0136\u0139\3\2"+
		"\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138`\3\2\2\2\u0139\u0137"+
		"\3\2\2\2\r\2\u00e5\u00ea\u00f1\u0101\u0106\u010f\u0119\u0124\u0130\u0137"+
		"\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}