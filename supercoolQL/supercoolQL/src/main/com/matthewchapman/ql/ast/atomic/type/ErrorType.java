package com.matthewchapman.ql.ast.atomic.type;

import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.validation.visitors.TypeVisitor;

/**
 * Created by matt on 13/03/2017.
 */
public class ErrorType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return false;
    }

    @Override
    public String toString() {
        return "ERRORTYPE";
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
