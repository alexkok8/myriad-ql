package org.uva.taxfree.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.uva.taxfree.ql.gen.QLGrammarBaseListener;
import org.uva.taxfree.ql.gen.QLGrammarLexer;
import org.uva.taxfree.ql.gen.QLGrammarParser;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.node.Node;
import org.uva.taxfree.ql.model.node.blocks.BlockNode;
import org.uva.taxfree.ql.model.node.blocks.FormNode;
import org.uva.taxfree.ql.model.node.blocks.IfElseStatementNode;
import org.uva.taxfree.ql.model.node.blocks.IfStatementNode;
import org.uva.taxfree.ql.model.node.declarations.CalculationNode;
import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.node.expression.ParenthesizedExpressionNode;
import org.uva.taxfree.ql.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.ql.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.ql.model.node.literal.StringLiteralNode;
import org.uva.taxfree.ql.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.ql.model.operators.*;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.StringType;
import org.uva.taxfree.ql.model.types.Type;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.uva.taxfree.ql.gen.QLGrammarParser.*;

public class QlAstBuilder extends QLGrammarBaseListener {

    private final AntlrBuilder mBuilder;
    private final List<ExpressionNode> mCachedConditions = new ArrayList<>();
    private final Stack<List<Node>> mChildsStack = new Stack<>();
    private FormNode mRootNode;

    public QlAstBuilder(File inputFile) {
        mBuilder = new AntlrBuilder(inputFile);
    }

    public FormNode generateAst(MessageList semanticsMessages) {
        try {
            QLGrammarParser parser;
            try {
                parser = createGrammarParser();
            } catch (IOException e) {
                semanticsMessages.addError("(QlAstBuilder.java:generateAst): Unable to create grammarParser: " + e.getMessage());
                return null;
            }

            parser.addErrorListener(mBuilder.createErrorListener());
            mBuilder.walkParseTree(this, parser.form());
            return mRootNode;
        } catch (UnsupportedOperationException e) {
            semanticsMessages.addError("(QlAstBuilder.java:generateAst): Couldn't generate AST because of a parse error: " + e.getMessage());
            return null;
        }
    }

    private QLGrammarParser createGrammarParser() throws IOException {
        QLGrammarLexer qlGrammarLexer = new QLGrammarLexer(mBuilder.createInputStream());
        return new QLGrammarParser(mBuilder.createTokenStream(qlGrammarLexer));
    }

    private ExpressionNode popCachedCondition() {
        return mCachedConditions.remove(mCachedConditions.size() - 1);
    }

    private void createStack() {
        mChildsStack.push(new ArrayList<>());
    }

    private List<Node> popChildStack() {
        return mChildsStack.pop();
    }

    private void addDeclaration(DeclarationNode node) {
        addToStack(node);
    }

    private void addToStack(Node node) {
        mChildsStack.peek().add(node);
    }

    private void addToStack(ExpressionNode node) {
        mCachedConditions.add(node);
    }

    private SourceInfo createSourceInfo(ParserRuleContext context) {
        return mBuilder.createSourceInfo(context);
    }

    // Enters
    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
        super.enterForm(ctx);
        createStack();
    }

    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
        super.enterQuestion(ctx);
        String questionText = ctx.LABEL().getText();
        String questionId = ctx.VARIABLE_LITERAL().getText();
        String varTypeText = ctx.varType().getText();
        Type questionType;
        if ("boolean".equals(varTypeText)) {
            questionType = new BooleanType();
        } else if ("string".equals(varTypeText)) {
            questionType = new StringType();
        } else if ("integer".equals(varTypeText)) {
            questionType = new IntegerType();
        } else {
            throw new TypeNotPresentException(varTypeText, new Throwable("This is an unsupported type for QL!"));
        }
        DeclarationNode questionNode = new DeclarationNode(questionText, questionId, questionType, createSourceInfo(ctx));
        addDeclaration(questionNode);
    }

    @Override
    public void enterBooleanLiteral(QLGrammarParser.BooleanLiteralContext ctx) {
        super.enterBooleanLiteral(ctx);
        ExpressionNode booleanLiteralNode = new BooleanLiteralNode(Boolean.valueOf(ctx.getText()), createSourceInfo(ctx));
        addToStack(booleanLiteralNode);
    }

    @Override
    public void enterStringLiteral(QLGrammarParser.StringLiteralContext ctx) {
        super.enterStringLiteral(ctx);
        ExpressionNode stringLiteralNode = new StringLiteralNode(ctx.getText(), createSourceInfo(ctx));
        addToStack(stringLiteralNode);
    }

    @Override
    public void enterIntegerLiteral(QLGrammarParser.IntegerLiteralContext ctx) {
        super.enterIntegerLiteral(ctx);
        ExpressionNode integerLiteralNode = new IntegerLiteralNode(Integer.valueOf(ctx.getText()), createSourceInfo(ctx));
        addToStack(integerLiteralNode);
    }

    @Override
    public void enterVarNameLiteral(QLGrammarParser.VarNameLiteralContext ctx) {
        super.enterVarNameLiteral(ctx);
        ExpressionNode varNameLiteral = new VariableLiteralNode(ctx.getText(), createSourceInfo(ctx));
        addToStack(varNameLiteral);
    }

    @Override
    public void enterIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.enterIfStatement(ctx);
        createStack();
    }

    @Override
    public void enterIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.enterIfElseStatement(ctx);
        createStack();
    }

    // Exits
    @Override
    public void exitCalculation(QLGrammarParser.CalculationContext ctx) {
        super.exitCalculation(ctx);
        String fieldDescription = ctx.LABEL().getText();
        String fieldId = ctx.VARIABLE_LITERAL().getText();

        String varTypeText = ctx.varType().getText();
        Type fieldType;
        if ("boolean".equals(varTypeText)) {
            fieldType = new BooleanType();
        } else if ("integer".equals(varTypeText)) {
            fieldType = new IntegerType();
        } else {
            throw new TypeNotPresentException(varTypeText, new Throwable("This is an unsupported type for QL!"));
        }
        DeclarationNode calculatedNode = new CalculationNode(fieldDescription, fieldId, fieldType, popCachedCondition(), createSourceInfo(ctx));
        addDeclaration(calculatedNode);
    }

    @Override
    public void exitBinaryExpression(QLGrammarParser.BinaryExpressionContext ctx) {
        super.exitBinaryExpression(ctx);
        Operator operator = createOperator(ctx.op);
        ExpressionNode rightExpressionNode = popCachedCondition();
        ExpressionNode booleanExpressionNode = new BinaryExpressionNode(popCachedCondition(), operator, rightExpressionNode, createSourceInfo(ctx));
        addToStack(booleanExpressionNode);
    }

    private Operator createOperator(Token op) {
        switch (op.getType()) {
            case OP_MULTIPLY:
                return new MultiplyOperator();
            case OP_DIVIDE:
                return new DivideOperator();
            case OP_PLUS:
                return new AddOperator();
            case OP_MINUS:
                return new SubtractOperator();
            case OP_SMALLER:
                return new LessThanOperator();
            case OP_SMALLEROREQUAL:
                return new LessEqualOperator();
            case OP_BIGGER:
                return new GreaterThanOperator();
            case OP_BIGGEROREQUAL:
                return new GreaterEqualOperator();
            case OP_EQUALS:
                return new EqualsOperator();
            case OP_NOTEQUALS:
                return new NotEqualsOperator();
            case OP_LOGICAL_AND:
                return new AndOperator();
            case OP_LOGICAL_OR:
                return new OrOperator();
            default:
                throw new TypeNotPresentException(op.getText(), new Throwable("This is an unsupported operator for QL!"));
        }
    }

    @Override
    public void exitParenthesizedExpression(QLGrammarParser.ParenthesizedExpressionContext ctx) {
        super.exitParenthesizedExpression(ctx);
        ExpressionNode parenthesizedExpressionNode = new ParenthesizedExpressionNode(popCachedCondition(), createSourceInfo(ctx));
        addToStack(parenthesizedExpressionNode);
    }

    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.exitIfStatement(ctx);
        BlockNode ifStatementNode = new IfStatementNode(popCachedCondition(), popChildStack(), createSourceInfo(ctx));
        addToStack(ifStatementNode);
    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.exitIfElseStatement(ctx);
        List<Node> allChildes = popChildStack();

        int splitIndex = ctx.thenStatements.size();
        List<Node> thenStatementNodes = new ArrayList<>(allChildes.subList(0, splitIndex));
        List<Node> elseStatementNodes = new ArrayList<>(allChildes.subList(splitIndex, allChildes.size()));

        BlockNode ifElseStatementNode = new IfElseStatementNode(popCachedCondition(), thenStatementNodes, elseStatementNodes, createSourceInfo(ctx));
        addToStack(ifElseStatementNode);
    }

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {
        super.exitForm(ctx);
        mRootNode = new FormNode(ctx.VARIABLE_LITERAL().getText(), popChildStack(), createSourceInfo(ctx));
        if (!mChildsStack.isEmpty()) {
            throw new AssertionError("Stack should be empty when we finished creating our AST.");
        }
    }
}
