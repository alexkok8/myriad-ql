package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

public abstract class Operator {
    public abstract boolean supports(Type left, Type right);

    public abstract Type getType();

    public String evaluate(ExpressionNode Left, ExpressionNode Right) {
        throw new RuntimeException("Not implemented yet");
    }
}