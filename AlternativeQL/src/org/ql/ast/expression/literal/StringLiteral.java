package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

public class StringLiteral extends AbstractLiteral<String> {
    public StringLiteral(String value) {
        super(value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
