package com.matthewchapman.ql.ast;

/**
 * Created by matt on 21/02/2017.
 * <p>
 * Base class for all nodes of the AST, provides no implementation. Exists
 * only for providing a common base for the Antlr visitors to return.
 */

public abstract class TreeNode {

    private int line;
    private int column;

    int getLine() {
        return this.line;
    }

    protected void setLine(int line) {
        this.line = line;
    }

    int getColumn() {
        return this.column;
    }

    protected void setColumn(int column) {
        this.column = column;
    }
}
