package org.ql.evaluator.value;

import java.util.Objects;

public class IntegerValue extends Value {
    private final Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public Value equal(Value comparable) {
        return comparable.equal(this);
    }

    @Override
    public Value equal(IntegerValue comparable) {
        return new BooleanValue(value.equals(comparable.value));
    }

    @Override
    public Value lowerThanOrEqual(Value comparable) {
        return comparable.lowerThanOrEqual(this);
    }

    @Override
    public Value lowerThanOrEqual(IntegerValue comparable) {
        return new BooleanValue(comparable.value <= value);
    }

    @Override
    public Value lowerThan(Value comparable) {
        return comparable.lowerThan(this);
    }

    @Override
    public Value lowerThan(IntegerValue comparable) {
        return new BooleanValue(comparable.value < value);
    }

    @Override
    public Value greaterThan(Value comparable) {
        return comparable.greaterThan(this);
    }

    @Override
    public Value greaterThan(IntegerValue comparable) {
        return new BooleanValue(comparable.value > value);
    }

    @Override
    public Value greaterThanOrEqual(Value comparable) {
        return comparable.greaterThanOrEqual(this);
    }

    @Override
    public Value greaterThanOrEqual(IntegerValue comparable) {
        return new BooleanValue(comparable.value >= value);
    }

    @Override
    public Value notEqual(Value comparable) {
        return comparable.notEqual(this);
    }

    @Override
    public Value notEqual(IntegerValue comparable) {
        return new BooleanValue(!Objects.equals(comparable.value, value));
    }

    @Override
    public Value product(Value multiplicand) {
        return multiplicand.product(this);
    }

    @Override
    public Value product(IntegerValue multiplier) {
        return new IntegerValue(multiplier.value * value);
    }

    @Override
    public Value increment() {
        return new IntegerValue(value + 1);
    }

    @Override
    public Value decrement() {
        return new IntegerValue(value - 1);
    }

    @Override
    public Value subtraction(Value subtrahend) {
        return subtrahend.subtraction(this);
    }

    @Override
    public Value subtraction(IntegerValue minuend) {
        return new IntegerValue(minuend.value - value);
    }

    @Override
    public Value division(Value divisor) {
        return divisor.division(this);
    }

    @Override
    public Value division(IntegerValue dividend) {

        if (value == 0) {
            return new UnknownValue();
        }

        return new IntegerValue(dividend.value / value);
    }

    @Override
    public Value addition(Value augend) {
        return augend.addition(this);
    }

    @Override
    public Value addition(IntegerValue addend) {
        return new IntegerValue(addend.value + value);
    }

    @Override
    public Integer getPlainValue() {
        return value;
    }

    @Override
    public Integer toInteger() {
        return value;
    }

    @Override
    public Double toDouble() {
        return value.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerValue that = (IntegerValue) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
