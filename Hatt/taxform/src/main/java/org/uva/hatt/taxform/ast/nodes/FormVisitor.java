package org.uva.hatt.taxform.ast.nodes;

import org.uva.hatt.taxform.ast.nodes.expressions.BinaryExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;

public interface FormVisitor<T> {
    T visit(Form node);
    T visit(Question node);
    T visit(ComputedQuestion node);
    T visit(IfThen node);
    T visit(IfThenElse node);
    T visit(Boolean node);
    T visit(Integer node);
    T visit(Money node);
    T visit(String node);
    T visit(ValueType node);
    T visit(BinaryExpression node);
    T visit(GroupedExpression node);
    T visit(Identifier identifier);
    T visit(StringerLiteral stringerLiteral);
    T visit(IntegerLiteral integerLiteral);
    T visit(BooleanLiteral booleanLiteral);
    T visit(Expression expression);
}
