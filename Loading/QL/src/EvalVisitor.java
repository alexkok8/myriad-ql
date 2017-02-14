

import ast.Visitor;
import ast.expression.BinaryExpression;
import ast.expression.Expression;
import ast.expression.UnaryExpression;

public class EvalVisitor extends Visitor {
	
	private Environment environment;
	
	public EvalVisitor(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	public void visit(BinaryExpression binaryExpression) {
		
		Expression result = binaryExpression.evaluate() ;	
		if (result == null) {
			// throw
		}
		
		System.out.println("Eval: " + result);
	}
	
	@Override
	public void visit(UnaryExpression unaryExpression) {
		
		System.out.println("Eval: " + unaryExpression.evaluate());
	}	
}
