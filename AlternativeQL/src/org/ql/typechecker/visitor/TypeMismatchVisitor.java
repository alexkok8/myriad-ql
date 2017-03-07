package org.ql.typechecker.visitor;

import org.ql.ast.Form;
import org.ql.ast.expression.BinaryExpression;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.typechecker.issues.IssuesStorage;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.error.NumberExpected;
import org.ql.typechecker.issues.error.TypeMismatch;
import org.ql.typechecker.issues.error.UndefinedSymbol;

public class TypeMismatchVisitor extends AbstractTypeCheckVisitor<Type, SymbolTable> {

    private final IssuesStorage issuesStorage;

    public TypeMismatchVisitor(IssuesStorage issuesStorage) {
        this.issuesStorage = issuesStorage;
    }

    @Override
    public Type visitForm(Form form, SymbolTable symbolTable) {
        visitStatements(form.getStatements(), null);
        return null;
    }

    @Override
    public Type visitIfThen(IfThen ifThen, SymbolTable symbolTable) {
        visitExpression(ifThen.getCondition(), null);
        visitStatements(ifThen.getThenStatements(), null);
        return null;
    }

    @Override
    public Type visitIfThenElse(IfThenElse ifThenElse, SymbolTable symbolTable) {
        visitExpression(ifThenElse.getCondition(), null);
        visitStatements(ifThenElse.getThenStatements(), null);
        visitStatements(ifThenElse.getElseStatements(), null);
        return null;
    }

    @Override
    public Type visitQuestion(Question question, SymbolTable symbolTable) {
        if (question.getValue() != null) {
            Type valueType = question.getValue().accept(this, null);

            if (!question.getType().isCompatibleWith(valueType)) {
                issuesStorage.addError(new TypeMismatch(question.getType(), valueType));
            }
        }
        return null;
    }

    @Override
    public Type visitNegation(Negation node, SymbolTable symbolTable) {
        Type innerExpressionType = node.getExpression().accept(this, null);

        if (!innerExpressionType.isBoolean()) {
            issuesStorage.addError(new TypeMismatch(new BooleanType(), innerExpressionType));
            return new UnknownType();
        }

        return new BooleanType();
    }

    @Override
    public Type visitProduct(Product node, SymbolTable symbolTable) {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visitIncrement(Increment node, SymbolTable symbolTable) {
        Type innerExpressionType = node.getExpression().accept(this, null);

        if (!(innerExpressionType.isNumeric())) {
            issuesStorage.addError(new NumberExpected(innerExpressionType));
            return new UnknownType();
        }

        return innerExpressionType;
    }

    @Override
    public Type visitSubtraction(Subtraction node, SymbolTable symbolTable) {
        return checkTypeMismatch(node);
    }

    @Override
    public BooleanType visitNotEqual(NotEqual node, SymbolTable symbolTable) {
        checkTypeMismatch(node);

        return new BooleanType();
    }

    @Override
    public Type visitAnd(LogicalAnd node, SymbolTable symbolTable) {
        return checkLogicalExpression(node);
    }

    @Override
    public BooleanType visitLowerThan(LowerThan node, SymbolTable symbolTable) {
        checkTypeMismatch(node);

        return new BooleanType();
    }

    @Override
    public BooleanType visitGreaterThanOrEqual(GreaterThanOrEqual node, SymbolTable symbolTable) {
        checkTypeMismatch(node);

        return new BooleanType();
    }

    @Override
    public Type visitDivision(Division node, SymbolTable symbolTable) {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visitParameter(Parameter parameter, SymbolTable symbolTable) {
        if (!symbolTable.isDeclared(parameter.getId())) {
            issuesStorage.addError(new UndefinedSymbol(parameter.getId()));
            return new UnknownType();
        }

        return symbolTable.lookup(parameter.getId());
    }

    @Override
    public Type visitGroup(Group node, SymbolTable symbolTable) {
        return node.getExpression().accept(this, null);
    }

    @Override
    public Type visitAddition(Addition node, SymbolTable symbolTable) {
        return checkTypeMismatch(node);
    }

    @Override
    public BooleanType visitGreaterThan(GreaterThan node, SymbolTable symbolTable) {
        checkTypeMismatch(node);

        return new BooleanType();
    }

    @Override
    public Type visitDecrement(Decrement node, SymbolTable symbolTable) {
        Type innerExpressionType = node.getExpression().accept(this, null);

        if (!(innerExpressionType.isNumeric())) {
            issuesStorage.addError(new NumberExpected(innerExpressionType));
            return new UnknownType();
        }

        return innerExpressionType;
    }

    @Override
    public BooleanType visitEquals(Equals node, SymbolTable symbolTable) {
        checkTypeMismatch(node);

        return new BooleanType();
    }

    @Override
    public BooleanType visitLowerThanOrEqual(LowerThanOrEqual node, SymbolTable symbolTable) {
        checkTypeMismatch(node);

        return new BooleanType();
    }

    @Override
    public Type visitOr(LogicalOr node, SymbolTable symbolTable) {
        return checkLogicalExpression(node);
    }

    @Override
    public BooleanType visitBoolean(BooleanLiteral node, SymbolTable symbolTable) {
        return new BooleanType();
    }

    @Override
    public FloatType visitDecimal(DecimalLiteral node, SymbolTable symbolTable) {
        return new FloatType();
    }

    @Override
    public IntegerType visitInteger(IntegerLiteral node, SymbolTable symbolTable) {
        return new IntegerType();
    }

    @Override
    public StringType visitString(StringLiteral node, SymbolTable symbolTable) {
        return new StringType();
    }

    private Type checkTypeMismatch(BinaryExpression node) {
        Type leftType = node.getLeft().accept(this, null);
        Type rightType = node.getRight().accept(this, null);

        if (!leftType.isCompatibleWith(rightType)) {
            issuesStorage.addError(new TypeMismatch(leftType, rightType));

            return new UnknownType();
        }

        return leftType;
    }

    private Type checkLogicalExpression(BinaryExpression node) {
        Type leftType = node.getLeft().accept(this, null);
        Type rightType = node.getRight().accept(this, null);

        if (!leftType.isBoolean()) {
            issuesStorage.addError(new TypeMismatch(new BooleanType(), leftType));

            return new UnknownType();
        }

        if (!rightType.isBoolean()) {
            issuesStorage.addError(new TypeMismatch(new BooleanType(), rightType));

            return new UnknownType();
        }

        return new BooleanType();
    }

}
