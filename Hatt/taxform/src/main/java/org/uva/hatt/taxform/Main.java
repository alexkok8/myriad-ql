package org.uva.hatt.taxform;

import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.hatt.taxform.ast.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.typeChecker.messages.Message;
import org.uva.hatt.taxform.ast.visitors.QLVisitor;
import org.uva.hatt.taxform.typeChecker.TypeChecker;

public class Main {

    public static void main(String[] args) throws Exception {
//        String qlForm = "form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: money }";
//        String qlForm = "form taxOfficeExample { \n\"Did you sell a house in 2010?\" hasSoldHouse: boolean \n\"Did you buy a house in 2010?\" hasBoughtHouse: boolean }";
//        String qlForm = "form taxOfficeExample { \n\"q1\" hasSoldHouse: boolean \n\"q1\" hasBoughtHouse: money }";
//        String qlForm = "form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: string if (hasSoldHouse) { \"What was the selling price?\" sellingPrice: money \"What was the selling price?\" sellingPrice: money } }";
//        String qlForm = "form taxOfficeExample { \"q2\" blbl: string if (hasSoldHouse) { \"q2\" sellingPrice: money \"q3\" sellingPrice: money } else { \"q4?\" a: boolean } }";
        String qlForm = "form taxOfficeExample { \"q2\" blbl: string if (1 + 1) { \"q2\" sellingPrice: money \"q3\" sellingPrice: money } else { \"q4?\" a: boolean } }";

        ParseTree tree = ASTGenerator.getParseTree(qlForm);

        QLVisitor visitor = new QLVisitor();
        visitor.visit(tree);

        Form form = visitor.getForm();
        System.out.println(form.toString());

        Message exceptionHandler = new Message();
        TypeChecker typeCheckerVisitor = new TypeChecker(exceptionHandler);
        typeCheckerVisitor.visit(form);

//        System.out.println(typeCheckerVisitor.getErrors().toString());
//        System.out.println(typeCheckerVisitor.getWarnings().toString());
    }

}
