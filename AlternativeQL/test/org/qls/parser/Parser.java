package org.qls.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ql.ast.Node;
import org.qls.ast.QLSASTBuilder;
import org.qls.ast.StyleSheet;
import org.qls.ast.page.Page;
import org.qls.grammar.QLSLexer;
import org.qls.grammar.QLSParser;
import org.qls.grammar.QLSVisitor;

public class Parser {
    private final QLSVisitor<Node> visitor = new QLSASTBuilder();

    public StyleSheet parseStyleSheet(String code) {
        return (StyleSheet) visitor.visit(createParser(code).stylesheet());
    }

    public Page parsePage(String code) {
        return (Page) visitor.visit(createParser(code).page());
    }

    private QLSParser createParser(String code) {
        return new QLSParser(new CommonTokenStream(new QLSLexer(new ANTLRInputStream(code))));
    }
}
