package org.uva.hatt.taxform.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.uva.hatt.taxform.ast.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.evaluation.EnvironmentsTable;
import org.uva.hatt.taxform.ast.visitors.QLVisitor;
import org.uva.hatt.taxform.ast.visitors.Visitor;
import org.uva.hatt.taxform.typechecker.CircularDependencyChecker;
import org.uva.hatt.taxform.typechecker.TypeChecker;
import org.uva.hatt.taxform.typechecker.messages.Message;
import org.uva.hatt.taxform.typechecker.messages.error.Error;

import java.io.IOException;

public class Taxform extends Application {

    private static final String defaultCode = String.join("\n", new String[]{
            "form taxOffice { " +
                    "\n" +
                    "\n" +
                    "}"
    });

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        CodeArea codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.replaceText(0, 0, defaultCode);
        codeArea.requestFocus();

        VirtualizedScrollPane<CodeArea> scrollPane = new VirtualizedScrollPane<>(codeArea);

        HBox hBox = new HBox();
        Button createFormButton = new Button("Create form");
        createFormButton.setOnAction(e -> {
            ParseTree tree = null;
            try {
                tree = ASTGenerator.getParseTree(codeArea.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            QLVisitor visitor = new QLVisitor();
            visitor.visit(tree);

            Form form = visitor.getForm();

            Message message = new Message();
            TypeChecker typeChecker = new TypeChecker(message);
            typeChecker.visit(form);

            CircularDependencyChecker circularDependencyChecker = new CircularDependencyChecker(message);
            circularDependencyChecker.visit(form);

            if (message.getErrors().isEmpty()) {

                if (!message.getWarnings().isEmpty()) {
                    Stage dialog = new Stage();
                    dialog.initModality(Modality.NONE);
                    dialog.initOwner(stage);

                    VBox dialogVbox = new VBox(10);

                    message.getWarnings().forEach(error -> dialogVbox.getChildren().add(new Text(error.getMessage())));

                    Scene dialogScene = new Scene(dialogVbox, 450, 300);

                    dialog.setScene(dialogScene);
                    dialog.show();
                }

                EnvironmentsTable environmentsTable = new EnvironmentsTable();
                Visitor uiVisitor = new UIVisitor(stage, environmentsTable);
                uiVisitor.visit(form);

                stage.show();
            } else {
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);

                VBox dialogVbox = new VBox(10);

                message.getErrors().forEach(error -> dialogVbox.getChildren().add(new Text(error.getMessage())));

                Scene dialogScene = new Scene(dialogVbox, 450, 300);

                dialog.setScene(dialogScene);
                dialog.show();
            }
        });

        hBox.getChildren().add(createFormButton);

        VBox vBox = new VBox();
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        vBox.getChildren().addAll(hBox, scrollPane);

        Scene scene = new Scene(vBox, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Ql Form Editor");
        stage.show();
    }
}