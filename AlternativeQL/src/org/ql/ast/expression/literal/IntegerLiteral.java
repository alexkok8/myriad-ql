package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

public class IntegerLiteral extends AbstractLiteral<Integer> {
    public IntegerLiteral(Integer value, Metadata metadata) {
        super(value);
        this.metadata = metadata;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
