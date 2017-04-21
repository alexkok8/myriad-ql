/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSDropdown.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.WidgetVisitor;

import java.util.*;
import java.util.List;

public class QLSDropdown extends QLSWidget {

    private String yesLabel;
    private String noLabel;

    public QLSDropdown(String label, String yes, String no, LineNumber lineNumber) {
        super(lineNumber);
        widgetLabel.setText(label);
        yesLabel = yes;
        noLabel = no;
    }

    @Override
    public List<Type> getSupportedQuestionTypes() {
        List<Type> supportedQuestionTypes = new ArrayList<>();
        supportedQuestionTypes.add(new BooleanType());
        return supportedQuestionTypes;
    }

    public <T> T accept(WidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}