package ql.ast.atom;

import ql.ast.ExpressionVisitor;

public class IntegerAtom extends Atom {
		
	private final int atom;

	public IntegerAtom(int atom, int line) {
		super(line);
		this.atom = atom;
	}

	public int getAtom() {
		return atom;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}