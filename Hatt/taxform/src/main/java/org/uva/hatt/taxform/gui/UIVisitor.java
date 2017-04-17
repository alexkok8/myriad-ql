package org.uva.hatt.taxform.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.ComputedQuestion;
import org.uva.hatt.taxform.ast.nodes.items.IfThen;
import org.uva.hatt.taxform.ast.nodes.items.IfThenElse;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.evaluation.EnvironmentsTable;
import org.uva.hatt.taxform.ast.nodes.FormVisitor;
import org.uva.hatt.taxform.evaluation.Evaluator;
import org.uva.hatt.taxform.gui.fields.*;
import org.uva.hatt.taxform.values.BooleanValue;
import org.uva.hatt.taxform.values.Value;

import java.util.List;
import java.util.stream.Collectors;

public class UIVisitor implements FormVisitor<Pane> {

    private final Stage stage;
    private final EnvironmentsTable environmentsTable;
    private Form form;
    private final Evaluator evaluator;

    UIVisitor(Stage stage, EnvironmentsTable environmentsTable) {
        this.stage = stage;
        this.environmentsTable = environmentsTable;
        evaluator = new Evaluator(environmentsTable);
    }

    @Override
    public Pane visit(Form node) {
        form = node;
        stage.setTitle(node.getFormId());

        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 800, 600);

        List<Pane> questions = node.getQuestions().stream().map(item -> item.accept(this)).collect(Collectors.toList());

        vBox.getChildren().addAll(questions);
        vBox.requestFocus();

        stage.setScene(scene);
        stage.sizeToScene();

        return null;
    }

    @Override
    public Pane visit(org.uva.hatt.taxform.ast.nodes.items.Question node) {
        Field widget = (Field) node.getType().accept(this);
        widget.setIdentifier(node.getValue());
        widget.setLabel(node.getQuestion());

        Value value = environmentsTable.find(node.getValue());
        widget.setValue(value);

        Question question = new Question(environmentsTable, this, form);
        question.addField(widget);

        return question;
    }

    @Override
    public Pane visit(ComputedQuestion node) {
        Field widget = (Field) node.getType().accept(this);
        widget.setIdentifier(node.getValue());
        widget.setLabel(node.getQuestion());

        Value value = evaluator.visit(node.getComputedValue());
        widget.setValue(value);
        widget.setReadOnly();

        Question question = new Question(environmentsTable, this, form);
        question.addField(widget);

        return question;
    }

    @Override
    public Pane visit(IfThen node) {
        VBox vBox = new VBox();

        Value value = evaluator.visit(node.getCondition());

        if (!value.isUndefined()) {
            BooleanValue booleanValue = (BooleanValue) value;

            if (booleanValue.getValue()) {
                List<Pane> thenStatements = node.getThenStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
                vBox.getChildren().addAll(thenStatements);
            }
        }

        return vBox;
    }

    @Override
    public Pane visit(IfThenElse node) {
        VBox vBox = new VBox();

        Value value = evaluator.visit(node.getCondition());

        if (!value.isUndefined()) {
            BooleanValue booleanValue = (BooleanValue) value;

            if (booleanValue.getValue()) {
                List<Pane> thenStatements = node.getThenStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
                vBox.getChildren().addAll(thenStatements);
            } else {
                List<Pane> elseStatements = node.getElseStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
                vBox.getChildren().addAll(elseStatements);
            }
        }

        return vBox;
    }

    @Override
    public Pane visit(Boolean node) {
        return new QLCheckBox();
    }

    @Override
    public Pane visit(Integer node) {
        return new QLInteger();
    }

    @Override
    public Pane visit(Money node) {
        return new QLMoney();
    }

    @Override
    public Pane visit(String node) {
        return new QLTextField();
    }

    @Override
    public Pane visit(ValueType node) {
        return null;
    }

    @Override
    public Pane visit(BooleanExpression node) {
        return null;
    }

    @Override
    public Pane visit(GroupedExpression node) {
        return null;
    }

    @Override
    public Pane visit(Identifier identifier) {
        return null;
    }

    @Override
    public Pane visit(StringerLiteral stringerLiteral) {
        return null;
    }

    @Override
    public Pane visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public Pane visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Pane visit(Expression expression) {
        return null;
    }

}
