import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
	public static void main(String[] args) throws Exception {

		 String tmp = "form Testing { Name: \"Question\" boolean }";
		
		 ANTLRInputStream input = new ANTLRInputStream( tmp );
		
		 QLLexer lexer = new QLLexer(input);
		
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		 QLParser parser = new QLParser(tokens);
		 ParseTree tree = parser.root(); // begin parsing at rule 'r'
		 System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	}
}