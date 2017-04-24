/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/styles/Width.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.styles;

import ql.astnodes.LineNumber;
import qls.visitorinterfaces.StyleVisitor;

public class Width extends StyleType {

    private static final String NAME = "width";

    public Width(Integer value, LineNumber lineNumber) {
        super(NAME, Integer.toString(value), lineNumber);
    }

    public <T> T accept(StyleVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
