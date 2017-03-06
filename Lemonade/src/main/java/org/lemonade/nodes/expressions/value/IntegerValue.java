package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IntegerValue extends NumericValue<Integer> implements Comparable<IntegerValue> {

    public IntegerValue(String value) {
        super(new QLIntegerType(), Integer.parseInt(value));
    }

    public IntegerValue(int value) {
        super(new QLIntegerType(), value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public IntegerValue plus(IntegerValue that) {
        return new IntegerValue(this.getValue() + that.getValue());
    }

    public DecimalValue plus(DecimalValue that) {
        return new DecimalValue(this.getValue() + that.getValue());
    }

    public MoneyValue plus(MoneyValue that) {
        return new MoneyValue(this.getValue() + that.getValue());
    }

    public IntegerValue product(IntegerValue that) {
        return new IntegerValue(this.getValue() * that.getValue());
    }

    public DecimalValue product(DecimalValue that) {
        return new DecimalValue(this.getValue() * that.getValue());
    }

    public MoneyValue product(MoneyValue that) {
        return new MoneyValue(this.getValue() * that.getValue());
    }

    public IntegerValue minus(IntegerValue that) {
        return new IntegerValue(this.getValue() - that.getValue());
    }

    public DecimalValue minus(DecimalValue that) {
        return new DecimalValue(this.getValue() - that.getValue());
    }

    public MoneyValue minus(MoneyValue that) {
        return new MoneyValue(this.getValue() - that.getValue());
    }

    public IntegerValue divide(IntegerValue that) {
        return new IntegerValue(this.getValue() / that.getValue());
    }

    public DecimalValue divide(DecimalValue that) {
        return new DecimalValue(this.getValue() / that.getValue());
    }

    public MoneyValue divide(MoneyValue that) {
        return new MoneyValue(this.getValue() / that.getValue());
    }

    @Override
    public BooleanValue gT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((IntegerValue) that) == 1);
    }

    @Override
    public BooleanValue gTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((IntegerValue) that) >= 0);
    }

    @Override
    public BooleanValue lT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((IntegerValue) that) == -1);
    }

    @Override
    public BooleanValue lTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((IntegerValue) that) <= 0);
    }

    @Override
    public IntegerValue neg() {
        return new IntegerValue(-this.getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntegerValue)) {
            return false;
        }
        IntegerValue that = (IntegerValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(IntegerValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}