package qls.ast.widget;

import QL.ast.type.IntegerType;
import QL.ast.type.Type;
import QL.ui.field.Field;
import qls.evaluation.Evaluator;

public class Spinbox extends Widget {

	public Spinbox(int line) {
		super(line);
	}

	@Override
	public Type getType() {
		return new IntegerType(1);
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
