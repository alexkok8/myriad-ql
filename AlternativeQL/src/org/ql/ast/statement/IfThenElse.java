package org.ql.ast.statement;

import org.ql.ast.Expression;
import org.ql.ast.Statement;

import java.util.List;

public class IfThenElse extends Statement {
    private final Expression condition;
    private final List<Statement> thenStatements;
    private final List<Statement> elseStatements;

    public IfThenElse(Expression condition, List<Statement> thenStatements, List<Statement> elseStatements) {
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getThenStatements() {
        return thenStatements;
    }

    public List<Statement> getElseStatements() {
        return elseStatements;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visitIfThenElse(this, context);
    }
}
