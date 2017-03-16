package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 08/03/2017.
 */
public class IntegerType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return type.toString().equals("integer");
    }

    @Override
    public String toString() {
        return "integer";
    }
}