//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     ANTLR Version: 4.6
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// Generated from C:\Users\Boukr\Documents\myriad-ql\WeekendWarriors\Questionnaires\Questionnaires\\QLS\Grammar\QLS.g4 by ANTLR 4.6

// Unreachable code detected
#pragma warning disable 0162
// The variable '...' is assigned but its value is never used
#pragma warning disable 0219
// Missing XML comment for publicly visible type or member '...'
#pragma warning disable 1591
// Ambiguous reference in cref attribute
#pragma warning disable 419

#pragma warning disable 3021
using System;
using System.Text;
using System.Diagnostics;
using System.Collections.Generic;
using Antlr4.Runtime;
using Antlr4.Runtime.Atn;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using DFA = Antlr4.Runtime.Dfa.DFA;

[System.CodeDom.Compiler.GeneratedCode("ANTLR", "4.6")]
[System.CLSCompliant(false)]
public partial class QLSParser : Parser {
	protected static DFA[] decisionToDFA;
	protected static PredictionContextCache sharedContextCache = new PredictionContextCache();
	public const int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		Whitespace=10, MultiLineComment=11, SingleLineComment=12, LiteralValue=13, 
		Type=14, Identifier=15, Property=16;
	public const int
		RULE_stylesheet = 0, RULE_page = 1, RULE_section = 2, RULE_widgetQuestion = 3, 
		RULE_widget = 4, RULE_question = 5, RULE_defaultStyle = 6, RULE_cssItem = 7;
	public static readonly string[] ruleNames = {
		"stylesheet", "page", "section", "widgetQuestion", "widget", "question", 
		"defaultStyle", "cssItem"
	};

	private static readonly string[] _LiteralNames = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'widget'", 
		"'question'", "'default'", "':'"
	};
	private static readonly string[] _SymbolicNames = {
		null, null, null, null, null, null, null, null, null, null, "Whitespace", 
		"MultiLineComment", "SingleLineComment", "LiteralValue", "Type", "Identifier", 
		"Property"
	};
	public static readonly IVocabulary DefaultVocabulary = new Vocabulary(_LiteralNames, _SymbolicNames);

	[NotNull]
	public override IVocabulary Vocabulary
	{
		get
		{
			return DefaultVocabulary;
		}
	}

	public override string GrammarFileName { get { return "QLS.g4"; } }

	public override string[] RuleNames { get { return ruleNames; } }

	public override string SerializedAtn { get { return _serializedATN; } }

	static QLSParser() {
		decisionToDFA = new DFA[_ATN.NumberOfDecisions];
		for (int i = 0; i < _ATN.NumberOfDecisions; i++) {
			decisionToDFA[i] = new DFA(_ATN.GetDecisionState(i), i);
		}
	}

	public QLSParser(ITokenStream input)
		: base(input)
	{
		Interpreter = new ParserATNSimulator(this, _ATN, decisionToDFA, sharedContextCache);
	}
	public partial class StylesheetContext : ParserRuleContext {
		public ITerminalNode Identifier() { return GetToken(QLSParser.Identifier, 0); }
		public PageContext[] page() {
			return GetRuleContexts<PageContext>();
		}
		public PageContext page(int i) {
			return GetRuleContext<PageContext>(i);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_stylesheet; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterStylesheet(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitStylesheet(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitStylesheet(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public StylesheetContext stylesheet() {
		StylesheetContext _localctx = new StylesheetContext(Context, State);
		EnterRule(_localctx, 0, RULE_stylesheet);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 16; Match(T__0);
			State = 17; Match(Identifier);
			State = 18; Match(T__1);
			State = 22;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while (_la==T__3) {
				{
				{
				State = 19; page();
				}
				}
				State = 24;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
			}
			State = 25; Match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class PageContext : ParserRuleContext {
		public ITerminalNode Identifier() { return GetToken(QLSParser.Identifier, 0); }
		public SectionContext[] section() {
			return GetRuleContexts<SectionContext>();
		}
		public SectionContext section(int i) {
			return GetRuleContext<SectionContext>(i);
		}
		public PageContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_page; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterPage(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitPage(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitPage(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public PageContext page() {
		PageContext _localctx = new PageContext(Context, State);
		EnterRule(_localctx, 2, RULE_page);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 27; Match(T__3);
			State = 28; Match(Identifier);
			State = 29; Match(T__1);
			State = 33;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while (_la==T__4) {
				{
				{
				State = 30; section();
				}
				}
				State = 35;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
			}
			State = 36; Match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class SectionContext : ParserRuleContext {
		public ITerminalNode Identifier() { return GetToken(QLSParser.Identifier, 0); }
		public QuestionContext[] question() {
			return GetRuleContexts<QuestionContext>();
		}
		public QuestionContext question(int i) {
			return GetRuleContext<QuestionContext>(i);
		}
		public DefaultStyleContext[] defaultStyle() {
			return GetRuleContexts<DefaultStyleContext>();
		}
		public DefaultStyleContext defaultStyle(int i) {
			return GetRuleContext<DefaultStyleContext>(i);
		}
		public SectionContext[] section() {
			return GetRuleContexts<SectionContext>();
		}
		public SectionContext section(int i) {
			return GetRuleContext<SectionContext>(i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_section; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterSection(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitSection(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitSection(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public SectionContext section() {
		SectionContext _localctx = new SectionContext(Context, State);
		EnterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 38; Match(T__4);
			State = 39; Match(Identifier);
			State = 40; Match(T__1);
			State = 46;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__7))) != 0)) {
				{
				State = 44;
				ErrorHandler.Sync(this);
				switch (TokenStream.LA(1)) {
				case T__6:
					{
					State = 41; question();
					}
					break;
				case T__7:
					{
					State = 42; defaultStyle();
					}
					break;
				case T__4:
					{
					State = 43; section();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				State = 48;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
			}
			State = 49; Match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class WidgetQuestionContext : ParserRuleContext {
		public QuestionContext question() {
			return GetRuleContext<QuestionContext>(0);
		}
		public WidgetContext widget() {
			return GetRuleContext<WidgetContext>(0);
		}
		public WidgetQuestionContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_widgetQuestion; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterWidgetQuestion(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitWidgetQuestion(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitWidgetQuestion(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public WidgetQuestionContext widgetQuestion() {
		WidgetQuestionContext _localctx = new WidgetQuestionContext(Context, State);
		EnterRule(_localctx, 6, RULE_widgetQuestion);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 51; question();
			State = 52; Match(T__1);
			State = 53; widget();
			State = 54; Match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class WidgetContext : ParserRuleContext {
		public ITerminalNode Type() { return GetToken(QLSParser.Type, 0); }
		public WidgetContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_widget; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterWidget(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitWidget(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitWidget(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public WidgetContext widget() {
		WidgetContext _localctx = new WidgetContext(Context, State);
		EnterRule(_localctx, 8, RULE_widget);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 56; Match(T__5);
			State = 57; Match(Type);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class QuestionContext : ParserRuleContext {
		public ITerminalNode Identifier() { return GetToken(QLSParser.Identifier, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_question; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterQuestion(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitQuestion(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitQuestion(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public QuestionContext question() {
		QuestionContext _localctx = new QuestionContext(Context, State);
		EnterRule(_localctx, 10, RULE_question);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 59; Match(T__6);
			State = 60; Match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class DefaultStyleContext : ParserRuleContext {
		public ITerminalNode LiteralValue() { return GetToken(QLSParser.LiteralValue, 0); }
		public WidgetContext widget() {
			return GetRuleContext<WidgetContext>(0);
		}
		public CssItemContext[] cssItem() {
			return GetRuleContexts<CssItemContext>();
		}
		public CssItemContext cssItem(int i) {
			return GetRuleContext<CssItemContext>(i);
		}
		public DefaultStyleContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_defaultStyle; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterDefaultStyle(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitDefaultStyle(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitDefaultStyle(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public DefaultStyleContext defaultStyle() {
		DefaultStyleContext _localctx = new DefaultStyleContext(Context, State);
		EnterRule(_localctx, 12, RULE_defaultStyle);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 62; Match(T__7);
			State = 63; Match(LiteralValue);
			State = 64; Match(T__1);
			State = 68;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while (_la==Identifier) {
				{
				{
				State = 65; cssItem();
				}
				}
				State = 70;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
			}
			State = 71; widget();
			State = 72; Match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class CssItemContext : ParserRuleContext {
		public ITerminalNode[] Identifier() { return GetTokens(QLSParser.Identifier); }
		public ITerminalNode Identifier(int i) {
			return GetToken(QLSParser.Identifier, i);
		}
		public CssItemContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_cssItem; } }
		public override void EnterRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.EnterCssItem(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IQLSListener typedListener = listener as IQLSListener;
			if (typedListener != null) typedListener.ExitCssItem(this);
		}
		public override TResult Accept<TResult>(IParseTreeVisitor<TResult> visitor) {
			IQLSVisitor<TResult> typedVisitor = visitor as IQLSVisitor<TResult>;
			if (typedVisitor != null) return typedVisitor.VisitCssItem(this);
			else return visitor.VisitChildren(this);
		}
	}

	[RuleVersion(0)]
	public CssItemContext cssItem() {
		CssItemContext _localctx = new CssItemContext(Context, State);
		EnterRule(_localctx, 14, RULE_cssItem);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 74; Match(Identifier);
			State = 75; Match(T__8);
			State = 76; Match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	private static string _serializedATN = _serializeATN();
	private static string _serializeATN()
	{
	    StringBuilder sb = new StringBuilder();
	    sb.Append("\x3\x430\xD6D1\x8206\xAD2D\x4417\xAEF1\x8D80\xAADD\x3\x12");
		sb.Append("Q\x4\x2\t\x2\x4\x3\t\x3\x4\x4\t\x4\x4\x5\t\x5\x4\x6\t\x6\x4");
		sb.Append("\a\t\a\x4\b\t\b\x4\t\t\t\x3\x2\x3\x2\x3\x2\x3\x2\a\x2\x17\n");
		sb.Append("\x2\f\x2\xE\x2\x1A\v\x2\x3\x2\x3\x2\x3\x3\x3\x3\x3\x3\x3\x3");
		sb.Append("\a\x3\"\n\x3\f\x3\xE\x3%\v\x3\x3\x3\x3\x3\x3\x4\x3\x4\x3\x4");
		sb.Append("\x3\x4\x3\x4\x3\x4\a\x4/\n\x4\f\x4\xE\x4\x32\v\x4\x3\x4\x3\x4");
		sb.Append("\x3\x5\x3\x5\x3\x5\x3\x5\x3\x5\x3\x6\x3\x6\x3\x6\x3\a\x3\a\x3");
		sb.Append("\a\x3\b\x3\b\x3\b\x3\b\a\b\x45\n\b\f\b\xE\bH\v\b\x3\b\x3\b\x3");
		sb.Append("\b\x3\t\x3\t\x3\t\x3\t\x3\t\x2\x2\n\x2\x4\x6\b\n\f\xE\x10\x2");
		sb.Append("\x2N\x2\x12\x3\x2\x2\x2\x4\x1D\x3\x2\x2\x2\x6(\x3\x2\x2\x2\b");
		sb.Append("\x35\x3\x2\x2\x2\n:\x3\x2\x2\x2\f=\x3\x2\x2\x2\xE@\x3\x2\x2");
		sb.Append("\x2\x10L\x3\x2\x2\x2\x12\x13\a\x3\x2\x2\x13\x14\a\x11\x2\x2");
		sb.Append("\x14\x18\a\x4\x2\x2\x15\x17\x5\x4\x3\x2\x16\x15\x3\x2\x2\x2");
		sb.Append("\x17\x1A\x3\x2\x2\x2\x18\x16\x3\x2\x2\x2\x18\x19\x3\x2\x2\x2");
		sb.Append("\x19\x1B\x3\x2\x2\x2\x1A\x18\x3\x2\x2\x2\x1B\x1C\a\x5\x2\x2");
		sb.Append("\x1C\x3\x3\x2\x2\x2\x1D\x1E\a\x6\x2\x2\x1E\x1F\a\x11\x2\x2\x1F");
		sb.Append("#\a\x4\x2\x2 \"\x5\x6\x4\x2! \x3\x2\x2\x2\"%\x3\x2\x2\x2#!\x3");
		sb.Append("\x2\x2\x2#$\x3\x2\x2\x2$&\x3\x2\x2\x2%#\x3\x2\x2\x2&\'\a\x5");
		sb.Append("\x2\x2\'\x5\x3\x2\x2\x2()\a\a\x2\x2)*\a\x11\x2\x2*\x30\a\x4");
		sb.Append("\x2\x2+/\x5\f\a\x2,/\x5\xE\b\x2-/\x5\x6\x4\x2.+\x3\x2\x2\x2");
		sb.Append(".,\x3\x2\x2\x2.-\x3\x2\x2\x2/\x32\x3\x2\x2\x2\x30.\x3\x2\x2");
		sb.Append("\x2\x30\x31\x3\x2\x2\x2\x31\x33\x3\x2\x2\x2\x32\x30\x3\x2\x2");
		sb.Append("\x2\x33\x34\a\x5\x2\x2\x34\a\x3\x2\x2\x2\x35\x36\x5\f\a\x2\x36");
		sb.Append("\x37\a\x4\x2\x2\x37\x38\x5\n\x6\x2\x38\x39\a\x5\x2\x2\x39\t");
		sb.Append("\x3\x2\x2\x2:;\a\b\x2\x2;<\a\x10\x2\x2<\v\x3\x2\x2\x2=>\a\t");
		sb.Append("\x2\x2>?\a\x11\x2\x2?\r\x3\x2\x2\x2@\x41\a\n\x2\x2\x41\x42\a");
		sb.Append("\xF\x2\x2\x42\x46\a\x4\x2\x2\x43\x45\x5\x10\t\x2\x44\x43\x3");
		sb.Append("\x2\x2\x2\x45H\x3\x2\x2\x2\x46\x44\x3\x2\x2\x2\x46G\x3\x2\x2");
		sb.Append("\x2GI\x3\x2\x2\x2H\x46\x3\x2\x2\x2IJ\x5\n\x6\x2JK\a\x5\x2\x2");
		sb.Append("K\xF\x3\x2\x2\x2LM\a\x11\x2\x2MN\a\v\x2\x2NO\a\x11\x2\x2O\x11");
		sb.Append("\x3\x2\x2\x2\a\x18#.\x30\x46");
	    return sb.ToString();
	}

	public static readonly ATN _ATN =
		new ATNDeserializer().Deserialize(_serializedATN.ToCharArray());


}