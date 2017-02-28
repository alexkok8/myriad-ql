package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class GEqExpression extends BinaryExpression {

	public GEqExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().greaterEq(getRhs().evaluate());
//	}
}