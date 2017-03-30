package org.uva.taxfree.qls;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.taxfree.ql.gen.QLSGrammarBaseListener;
import org.uva.taxfree.ql.gen.QLSGrammarParser;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.StringType;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.*;
import org.uva.taxfree.qls.styleoption.widget.*;

import java.util.ArrayList;
import java.util.List;

public class QlsGrammarListener extends QLSGrammarBaseListener {

    private final List<Page> mPages;
    private final List<Section> mCachedSections;
    private final List<DefaultStyle> mCachedDefaultStyles;
    private final List<QuestionStyle> mCachedQuestionStyles;
    private final List<StyleOption> mCachedStyleOptions;
    private StyleOption mCachedWidgetStyleOption;

    public QlsGrammarListener() {
        mPages = new ArrayList<>();
        mCachedSections = new ArrayList<>();
        mCachedDefaultStyles = new ArrayList<>();
        mCachedQuestionStyles = new ArrayList<>();
        mCachedStyleOptions = new ArrayList<>();
    }

    private void addStyleOption(StyleOption styleOption) {
        mCachedStyleOptions.add(styleOption);
    }

    private void cacheWidgetStyle(WidgetStyleOption widgetStyleOption) {
        mCachedWidgetStyleOption = widgetStyleOption;
    }

    private List<StyleOption> popCachedStyles() {
        List<StyleOption> cachedStyles = new ArrayList<>(mCachedStyleOptions);
        mCachedStyleOptions.clear();
        return cachedStyles;
    }

    private List<QuestionStyle> popCachedQuestionStyles() {
        List<QuestionStyle> cachedQuestionStyles = new ArrayList<>(mCachedQuestionStyles);
        mCachedQuestionStyles.clear();
        return cachedQuestionStyles;
    }

    private List<DefaultStyle> popCachedDefaultStyles() {
        List<DefaultStyle> cachedDefaultStyles = new ArrayList<>(mCachedDefaultStyles);
        mCachedDefaultStyles.clear();
        return cachedDefaultStyles;
    }

    private List<Section> popCachedSections() {
        List<Section> cachedSections = new ArrayList<>(mCachedSections);
        mCachedSections.clear();
        return cachedSections;
    }

    private SourceInfo createSourceInfo(ParserRuleContext context) {
        // TODO: Remove duplication...
        int startLineNumber = context.getStart().getLine();
        int startColumn = context.getStart().getCharPositionInLine();
        int endLineNumber = context.getStop().getLine();
        int endColumn = calcEndColumn(startLineNumber, startColumn, endLineNumber, context);
        return new SourceInfo(startLineNumber, startColumn, endLineNumber, endColumn);
    }

    private int calcEndColumn(int startLineNumber, int endLineNumber, int startColumn, ParserRuleContext context) {
        int endColumn = context.getStop().getCharPositionInLine();
        if (startLineNumber == endLineNumber && startColumn == endColumn) {
            // Literals have an invalid endColumn, so we need to calculate it by ourselves
            endColumn = startColumn + context.getText().length();
        }
        return endColumn;
    }

    // Enters
    @Override
    public void enterCheckboxWidget(QLSGrammarParser.CheckboxWidgetContext ctx) {
        super.enterCheckboxWidget(ctx);
        cacheWidgetStyle(new CheckboxWidget(createSourceInfo(ctx)));
    }

    @Override
    public void enterDropdownWidget(QLSGrammarParser.DropdownWidgetContext ctx) {
        super.enterDropdownWidget(ctx);
        cacheWidgetStyle(new DropdownWidget(ctx.textTrue.getText(), ctx.textFalse.getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterRadioWidget(QLSGrammarParser.RadioWidgetContext ctx) {
        super.enterRadioWidget(ctx);
        cacheWidgetStyle(new RadioWidget(ctx.textTrue.getText(), ctx.textFalse.getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterSliderWidget(QLSGrammarParser.SliderWidgetContext ctx) {
        super.enterSliderWidget(ctx);
        cacheWidgetStyle(new SliderWidget(createSourceInfo(ctx)));
    }

    @Override
    public void enterSpinboxWidget(QLSGrammarParser.SpinboxWidgetContext ctx) {
        super.enterSpinboxWidget(ctx);
        cacheWidgetStyle(new SpinboxWidget(createSourceInfo(ctx)));
    }

    @Override
    public void enterTextWidget(QLSGrammarParser.TextWidgetContext ctx) {
        super.enterTextWidget(ctx);
        cacheWidgetStyle(new TextWidget(createSourceInfo(ctx)));
    }

    @Override
    public void enterFontStyle(QLSGrammarParser.FontStyleContext ctx) {
        super.enterFontStyle(ctx);
        addStyleOption(new FontStyleOption(ctx.STRING_LITERAL().getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterFontsizeStyle(QLSGrammarParser.FontsizeStyleContext ctx) {
        super.enterFontsizeStyle(ctx);
        addStyleOption(new FontSizeStyleOption(Integer.valueOf(ctx.INTEGER_LITERAL().getText()), createSourceInfo(ctx)));
    }

    @Override
    public void enterColorStyle(QLSGrammarParser.ColorStyleContext ctx) {
        super.enterColorStyle(ctx);
        addStyleOption(new ColorStyleOption(ctx.COLOR_LITERAL().getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterBackgroundColorStyle(QLSGrammarParser.BackgroundColorStyleContext ctx) {
        super.enterBackgroundColorStyle(ctx);
        addStyleOption(new BackgroundColorStyleOption(ctx.COLOR_LITERAL().getText(), createSourceInfo(ctx)));
    }

    // Exits
    @Override
    public void exitWidgetStyle(QLSGrammarParser.WidgetStyleContext ctx) {
        super.exitWidgetStyle(ctx);
        addStyleOption(mCachedWidgetStyleOption);
    }

    @Override
    public void exitQuestion(QLSGrammarParser.QuestionContext ctx) {
        super.exitQuestion(ctx);
        QuestionStyle questionStyle = new QuestionStyle(ctx.VARIABLE_LITERAL().getText(), popCachedStyles(), createSourceInfo(ctx));
        mCachedQuestionStyles.add(questionStyle);
    }

    @Override
    public void exitDefaultStyle(QLSGrammarParser.DefaultStyleContext ctx) {
        super.exitDefaultStyle(ctx);
        String varTypeText = ctx.varType().getText();
        Type type;
        if ("boolean".equals(varTypeText)) {
            type = new BooleanType();
        } else if ("string".equals(varTypeText)) {
            type = new StringType();
        } else if ("integer".equals(varTypeText)) {
            type = new IntegerType();
        } else {
            throw new TypeNotPresentException(varTypeText, new Throwable("This is an unsupported type for QLS!"));
        }

        mCachedDefaultStyles.add(new DefaultStyle(type, popCachedStyles(), createSourceInfo(ctx)));
    }

    @Override
    public void exitSection(QLSGrammarParser.SectionContext ctx) {
        super.exitSection(ctx);
        Section section = new Section(ctx.STRING_LITERAL().getText(), popCachedQuestionStyles(), createSourceInfo(ctx));
        mCachedSections.add(section);
    }

    @Override
    public void exitPage(QLSGrammarParser.PageContext ctx) {
        super.exitPage(ctx);
        Page page = new Page(ctx.VARIABLE_LITERAL().getText(), popCachedSections(), popCachedDefaultStyles(), createSourceInfo(ctx));
        mPages.add(page);
    }
}
