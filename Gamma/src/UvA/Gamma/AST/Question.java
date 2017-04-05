package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.Pair;
import UvA.Gamma.Validation.TypeChecker;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Question implements FormItem {
    private String question;
    private Identifier id;
    private Type type;

    public Question(String question, Identifier id, Type type) {
        this.question = question;
        this.id = id;
        this.type = type;
    }

    public String getQuestion() {
        assert question != null;
        return question;
    }

    public boolean check(TypeChecker checker, String newValue) {
        assert type != null;
        return false;
//        return checker.check(type, newValue);
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String validateRedeclaration(FormItem item) {
//        return item != this && item.hasId(this.id) ? this.id : null;
        return null;
    }

//    @Override
//    public Value.Type validateIdentifierType(String identifier, Value.Type type) {
//        assert type != null;
////        return this.id.equals(identifier) && !value.conformsToType(type) ? value.getType() : null;
//        return Value.T;
//    }

    @Override
    public Pair<String> validateCyclicDependency(FormItem item) {
        assert id != null;
//        return new Pair<>(item.isDependentOn(this.id) ? this.id : null, item.isDependencyOf(this));
        return null;
    }

    @Override
    public String validateLabel(FormItem item) {
        assert question != null;
//        return item != this && item.containsLabel(this.question) ? this.id : null;
        return null;
    }

    @Override
    public boolean containsLabel(String label) {
        assert question != null;
        return this.question.equals(label);
    }

    @Override
    public boolean isDependentOn(String id) {
        return false; // A question is never dependent on another item
    }

    @Override
    public String isDependencyOf(FormItem item) {
        assert id != null;
//        return item.isDependentOn(this.id) ? this.id : null;
        return null;
    }

    @Override
    public void show(FXMLController screen) {
//        value.showQuestion(screen, this);
    }

    @Override
    public boolean hasId(String id) {
        assert id != null;
        return this.id.equals(id);
    }

    @Override
    public boolean containsId(String id) {
        return hasId(id);
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {
        // I don't care about that, I am an independent question and don't need your help
    }

    @Override
    public String toString() {
        return "<Question>: " + question + " " + id + ": " + type;
    }
}
