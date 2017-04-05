package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Types.BooleanType;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 21-03-17.
 */
public class BooleanValue extends Value<BooleanValue> {
    private boolean value;

    public BooleanValue(String value) {
        this.value = Boolean.valueOf(value);
    }

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public BooleanValue and(BooleanValue other) {
        return new BooleanValue(this.value && other.value);
    }

    public BooleanValue or(BooleanValue other) {
        return new BooleanValue(this.value || other.value);
    }

    @Override
    public BooleanValue equals(BooleanValue other) {
        return new BooleanValue(this.value == other.value);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public BooleanValue not() {
        return new BooleanValue(!this.value);
    }

    @Override
    public Value value() {
        return this;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
