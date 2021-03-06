/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/evaluation/Evaluator.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.evaluation;

import ql.astnodes.expressions.Expression;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.Value;

public class Evaluator {

    private final Context context;
    private final ExpressionEvaluator expressionEvaluator;

    public Evaluator(Context context) {
        this.context = context;
        this.expressionEvaluator = new ExpressionEvaluator(context);
    }

    public Value getValueComputedQuestion(ComputedQuestion computedQuestion) {
        Expression expression = computedQuestion.getExpression();
        Value expressionValue = expression.accept(expressionEvaluator);
        context.addValue(computedQuestion.getIdentifierName(), expressionValue);
        return expressionValue;
    }

    public Value getValueIfStatement(IfStatement ifStatement) {
        Expression condition = ifStatement.getExpression();
        return condition.accept(expressionEvaluator);
    }
}
