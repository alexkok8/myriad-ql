package org.ql.ast.type;

import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

public class MoneyType extends Type {

    public MoneyType(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
