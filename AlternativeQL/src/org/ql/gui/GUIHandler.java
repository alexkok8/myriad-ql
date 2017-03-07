package org.ql.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.evaluator.Evaluator;
import org.ql.evaluator.ValueTable;
import org.ql.parser.Parser;
import org.ql.typechecker.TypeChecker;
import org.ql.typechecker.issues.IssuesStorage;

public class GUIHandler extends Application {

    private MainStage mainStage;

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parser parser = new Parser();
        Form form = parser.parseForm("form TestForm {" +
                "boolean hasSoldHouse: \"Did you sell a house in 2010?\" = true;" +
                "boolean hasBoughtHouse: \"Did you buy a house in 2010?\";" +
                    "if (hasSoldHouse) {" +
                    "boolean sellingPrice: \"What was the selling price?\" = true;" +
                    "string youDidWhat: \"How are you doin?\";" +
                        "if (youDidWhat==\"fine\") {" +
                            "boolean yay: \"Yay you're fine right?\";" +
                        "}" +
                    "}" +
                "boolean everythingAlright: \"Is everything alright sir?\" = true;" +
                "}");

        IssuesStorage issuesStorage = new IssuesStorage();
        TypeChecker typeChecker = new TypeChecker(form, issuesStorage);
        typeChecker.checkForm();

        if(issuesStorage.hasErrors()) {
            System.out.println("An issuesStorage was found!");
        } else {
            ValueTable valueTable = new ValueTable();
            Evaluator evaluator = new Evaluator(valueTable);
            evaluator.visitForm(form, null);

            primaryStage.show();
            MainStage mainStage = new MainStage(primaryStage);
            GUIVisitor guiVisitor = new GUIVisitor(mainStage, valueTable);
            guiVisitor.visitForm(form, null);
        }
    }
}
