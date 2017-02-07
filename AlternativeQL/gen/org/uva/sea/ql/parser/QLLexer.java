// Generated from C:/Users/AJ/Documents/SoftwareConstruction/myriad-ql/AlternativeQL/src/org/uva/sea/ql/parser\QL.g4 by ANTLR 4.6
package org.uva.sea.ql.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T_ASSIGN=19, T_IF=20, T_ELSE=21, T_STRING=22, T_COLON=23, T_FORM=24, 
		T_NAME=25, T_MONEY=26, T_FLOAT=27, T_INTEGER=28, T_BOOLEAN=29, T_OPEN_BRACKET=30, 
		T_CLOSE_BRACKET=31, T_OPEN_PARENT=32, T_CLOSE_PARENT=33, LINE_COMMENT=34, 
		T_SEMICOLON=35, WS=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T_ASSIGN", "T_IF", "T_ELSE", "T_STRING", "T_COLON", "T_FORM", 
		"T_NAME", "T_MONEY", "T_FLOAT", "T_INTEGER", "T_BOOLEAN", "T_OPEN_BRACKET", 
		"T_CLOSE_BRACKET", "T_OPEN_PARENT", "T_CLOSE_PARENT", "LINE_COMMENT", 
		"T_SEMICOLON", "WS", "T_ESC", "T_UNICODE", "T_HEX"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'!'", "'*'", "'+'", "'-'", "'/'", "'>'", "'<'", "'=='", "'!='", 
		"'<='", "'>='", "'&&'", "'||'", "'boolean'", "'float'", "'money'", "'string'", 
		"'integer'", "'='", "'if'", "'else'", null, "':'", "'form'", null, null, 
		null, null, null, "'{'", "'}'", "'('", "')'", null, "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "T_ASSIGN", "T_IF", "T_ELSE", 
		"T_STRING", "T_COLON", "T_FORM", "T_NAME", "T_MONEY", "T_FLOAT", "T_INTEGER", 
		"T_BOOLEAN", "T_OPEN_BRACKET", "T_CLOSE_BRACKET", "T_OPEN_PARENT", "T_CLOSE_PARENT", 
		"LINE_COMMENT", "T_SEMICOLON", "WS"
	};
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


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u00f4\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\7\27\u00a2\n\27\f\27\16\27\u00a5\13\27\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\6\32\u00b1\n\32\r\32\16\32\u00b2\3\33\3\33\3"+
		"\33\5\33\u00b8\n\33\3\34\3\34\3\34\5\34\u00bd\n\34\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u00ca\n\36\3\37\3\37\3 \3 \3"+
		"!\3!\3\"\3\"\3#\3#\3#\3#\7#\u00d8\n#\f#\16#\u00db\13#\3#\3#\3$\3$\3%\6"+
		"%\u00e2\n%\r%\16%\u00e3\3%\3%\3&\3&\3&\5&\u00eb\n&\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\3(\3(\2\2)\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\2M\2O\2\3\2\t\4\2$$^^\6\2\62;C\\aac"+
		"|\3\2\62;\4\2\f\f\17\17\5\2\13\f\16\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2"+
		"\62;CHch\u00f9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3Q\3\2\2\2\5S\3\2\2\2\7U\3\2\2\2\tW\3\2\2"+
		"\2\13Y\3\2\2\2\r[\3\2\2\2\17]\3\2\2\2\21_\3\2\2\2\23b\3\2\2\2\25e\3\2"+
		"\2\2\27h\3\2\2\2\31k\3\2\2\2\33n\3\2\2\2\35q\3\2\2\2\37y\3\2\2\2!\177"+
		"\3\2\2\2#\u0085\3\2\2\2%\u008c\3\2\2\2\'\u0094\3\2\2\2)\u0096\3\2\2\2"+
		"+\u0099\3\2\2\2-\u009e\3\2\2\2/\u00a8\3\2\2\2\61\u00aa\3\2\2\2\63\u00b0"+
		"\3\2\2\2\65\u00b4\3\2\2\2\67\u00b9\3\2\2\29\u00be\3\2\2\2;\u00c9\3\2\2"+
		"\2=\u00cb\3\2\2\2?\u00cd\3\2\2\2A\u00cf\3\2\2\2C\u00d1\3\2\2\2E\u00d3"+
		"\3\2\2\2G\u00de\3\2\2\2I\u00e1\3\2\2\2K\u00e7\3\2\2\2M\u00ec\3\2\2\2O"+
		"\u00f2\3\2\2\2QR\7#\2\2R\4\3\2\2\2ST\7,\2\2T\6\3\2\2\2UV\7-\2\2V\b\3\2"+
		"\2\2WX\7/\2\2X\n\3\2\2\2YZ\7\61\2\2Z\f\3\2\2\2[\\\7@\2\2\\\16\3\2\2\2"+
		"]^\7>\2\2^\20\3\2\2\2_`\7?\2\2`a\7?\2\2a\22\3\2\2\2bc\7#\2\2cd\7?\2\2"+
		"d\24\3\2\2\2ef\7>\2\2fg\7?\2\2g\26\3\2\2\2hi\7@\2\2ij\7?\2\2j\30\3\2\2"+
		"\2kl\7(\2\2lm\7(\2\2m\32\3\2\2\2no\7~\2\2op\7~\2\2p\34\3\2\2\2qr\7d\2"+
		"\2rs\7q\2\2st\7q\2\2tu\7n\2\2uv\7g\2\2vw\7c\2\2wx\7p\2\2x\36\3\2\2\2y"+
		"z\7h\2\2z{\7n\2\2{|\7q\2\2|}\7c\2\2}~\7v\2\2~ \3\2\2\2\177\u0080\7o\2"+
		"\2\u0080\u0081\7q\2\2\u0081\u0082\7p\2\2\u0082\u0083\7g\2\2\u0083\u0084"+
		"\7{\2\2\u0084\"\3\2\2\2\u0085\u0086\7u\2\2\u0086\u0087\7v\2\2\u0087\u0088"+
		"\7t\2\2\u0088\u0089\7k\2\2\u0089\u008a\7p\2\2\u008a\u008b\7i\2\2\u008b"+
		"$\3\2\2\2\u008c\u008d\7k\2\2\u008d\u008e\7p\2\2\u008e\u008f\7v\2\2\u008f"+
		"\u0090\7g\2\2\u0090\u0091\7i\2\2\u0091\u0092\7g\2\2\u0092\u0093\7t\2\2"+
		"\u0093&\3\2\2\2\u0094\u0095\7?\2\2\u0095(\3\2\2\2\u0096\u0097\7k\2\2\u0097"+
		"\u0098\7h\2\2\u0098*\3\2\2\2\u0099\u009a\7g\2\2\u009a\u009b\7n\2\2\u009b"+
		"\u009c\7u\2\2\u009c\u009d\7g\2\2\u009d,\3\2\2\2\u009e\u00a3\7$\2\2\u009f"+
		"\u00a2\5K&\2\u00a0\u00a2\n\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a0\3\2\2"+
		"\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7$\2\2\u00a7.\3\2\2\2\u00a8\u00a9"+
		"\7<\2\2\u00a9\60\3\2\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad"+
		"\7t\2\2\u00ad\u00ae\7o\2\2\u00ae\62\3\2\2\2\u00af\u00b1\t\3\2\2\u00b0"+
		"\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\t\4\2\2\u00b5\u00b7\13\2\2\2\u00b6\u00b8"+
		"\t\4\2\2\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\66\3\2\2\2\u00b9"+
		"\u00ba\t\4\2\2\u00ba\u00bc\13\2\2\2\u00bb\u00bd\t\4\2\2\u00bc\u00bb\3"+
		"\2\2\2\u00bc\u00bd\3\2\2\2\u00bd8\3\2\2\2\u00be\u00bf\t\4\2\2\u00bf:\3"+
		"\2\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3\7w\2\2\u00c3"+
		"\u00ca\7g\2\2\u00c4\u00c5\7h\2\2\u00c5\u00c6\7c\2\2\u00c6\u00c7\7n\2\2"+
		"\u00c7\u00c8\7u\2\2\u00c8\u00ca\7g\2\2\u00c9\u00c0\3\2\2\2\u00c9\u00c4"+
		"\3\2\2\2\u00ca<\3\2\2\2\u00cb\u00cc\7}\2\2\u00cc>\3\2\2\2\u00cd\u00ce"+
		"\7\177\2\2\u00ce@\3\2\2\2\u00cf\u00d0\7*\2\2\u00d0B\3\2\2\2\u00d1\u00d2"+
		"\7+\2\2\u00d2D\3\2\2\2\u00d3\u00d4\7\61\2\2\u00d4\u00d5\7\61\2\2\u00d5"+
		"\u00d9\3\2\2\2\u00d6\u00d8\n\5\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3\2"+
		"\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00dc\u00dd\b#\2\2\u00ddF\3\2\2\2\u00de\u00df\7=\2\2\u00df"+
		"H\3\2\2\2\u00e0\u00e2\t\6\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2"+
		"\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6"+
		"\b%\3\2\u00e6J\3\2\2\2\u00e7\u00ea\7^\2\2\u00e8\u00eb\t\7\2\2\u00e9\u00eb"+
		"\5M\'\2\u00ea\u00e8\3\2\2\2\u00ea\u00e9\3\2\2\2\u00ebL\3\2\2\2\u00ec\u00ed"+
		"\7w\2\2\u00ed\u00ee\5O(\2\u00ee\u00ef\5O(\2\u00ef\u00f0\5O(\2\u00f0\u00f1"+
		"\5O(\2\u00f1N\3\2\2\2\u00f2\u00f3\t\b\2\2\u00f3P\3\2\2\2\f\2\u00a1\u00a3"+
		"\u00b2\u00b7\u00bc\u00c9\u00d9\u00e3\u00ea\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}