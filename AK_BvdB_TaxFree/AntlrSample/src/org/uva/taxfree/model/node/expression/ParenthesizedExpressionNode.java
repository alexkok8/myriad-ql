package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.types.Type;

import java.util.Set;

public class ParenthesizedExpressionNode extends ExpressionNode {
    private final ExpressionNode mCondition;

    public ParenthesizedExpressionNode(ExpressionNode condition) {
        super();
        mCondition = condition;
    }

    @Override
    public String resolveValue() {
        return mCondition.resolveValue();
    }

    @Override
    public void addUsedVariables(Set<String> set) {
        mCondition.addUsedVariables(set);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Type getType() {
        return mCondition.getType();
    }
}
