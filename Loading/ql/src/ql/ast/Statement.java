package ql.ast;

import ql.ast.expression.Expression;

public class Statement extends BlockItem {

	private final Expression expression;
	private final Block block;
	
	public Statement(Expression expression, Block block, int line) {
		super(line);
		this.expression = expression;
		this.block = block;
	}

	public Expression getExpression() {
		return expression;
	}

	public Block getBlock() {
		return block;
	}

	@Override
	public void accept(FormVisitor v) {
		v.visit(this);
	}
}
