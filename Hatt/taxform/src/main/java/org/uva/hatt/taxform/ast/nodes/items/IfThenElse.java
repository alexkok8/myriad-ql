package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.FormVisitor;

import java.util.List;

public class IfThenElse extends Item{

    private final Expression condition;
    private final List<Item> thenStatements;
    private final List<Item> elseStatements;

    public IfThenElse(int lineNumber, Expression condition, List<Item> thenStatements, List<Item> elseStatements) {
        super(lineNumber);
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Item> getThenStatements() {
        return thenStatements;
    }

    public List<Item> getElseStatements() {
        return elseStatements;
    }

    @Override
    public <T> T accept(FormVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
