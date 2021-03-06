package org.lemonade.nodes.types;

import org.lemonade.visitors.interfaces.TypeVisitor;

public class QLIntegerType extends QLNumberType {

    public QLIntegerType() {
        super(1);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "integer";
    }
}
