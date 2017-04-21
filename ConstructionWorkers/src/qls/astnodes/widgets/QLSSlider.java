/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSSlider.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.MoneyType;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.WidgetVisitor;

import java.util.*;
import java.util.List;

public class QLSSlider extends QLSWidget {

    public QLSSlider(String label, LineNumber lineNumber) {
        super(lineNumber);
        widgetLabel.setText(label);
    }

    @Override
    public List<Type> getSupportedQuestionTypes() {
        List<Type> supportedQuestionTypes = new ArrayList<>();
        supportedQuestionTypes.add(new IntegerType());
        supportedQuestionTypes.add(new MoneyType());
        return supportedQuestionTypes;
    }

    public <T> T accept(WidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
