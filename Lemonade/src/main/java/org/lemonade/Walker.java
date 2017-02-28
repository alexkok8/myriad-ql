package org.lemonade;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.StringReader;

/**
 *
 */
public class Walker {
    public static void main(String[] args) throws Exception{
        String simpleForm = "form naam {tmp : \"echt?\" boolean}";
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(simpleForm));

        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.form();
        ParseTreeWalker walker = new ParseTreeWalker();
        QLListener listener = new MakeForm();
        walker.walk(listener, tree);
    }
}