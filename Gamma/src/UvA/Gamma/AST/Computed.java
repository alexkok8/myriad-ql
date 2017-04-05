package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.Pair;
import UvA.Gamma.Visitors.Visitor;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Computed implements FormItem {
    private String label;
    private Identifier id;
    public Expression expression;

    public Computed(String label, Identifier id, Expression expression) {
        this.label = label;
        this.id = id;
        this.expression = expression;
    }

    public String getLabel() {
        assert label != null;
        return label;
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {
//        assert expression != null;
//        if (expression.idChanged(changed.isDependencyOf(this), value)) {
//            root.idChanged(this, this.expression.toString());
//        }
    }

    public void accept(Visitor visitor) {
        expression.accept(visitor);
        visitor.visit(this);
    }

//    @Override
//    public Value.Type validateIdentifierType(String identifier, Value.Type type) {
//        assert id != null && expression != null;
//        return id.equals(identifier) && !expression.getValue().conformsToType(type) ? expression.getValue().getType() : null;
//    }

    @Override
    public String validateRedeclaration(FormItem item) {
        assert id != null;
//        return item != this && item.hasId(this.id) ? this.id : null;
        return null;
    }

    @Override
    public String validateLabel(FormItem item) {
        assert label != null;
//        return item != this && item.containsLabel(this.label) ? this.id : null;
        return null;
    }

    @Override
    public boolean containsLabel(String label) {
        assert label != null;
        return this.label.equals(label);
    }

    @Override
    public Pair<String> validateCyclicDependency(FormItem item) {
        assert id != null;
//        return new Pair<>(item.isDependentOn(this.id) ? this.id : null, item.isDependencyOf(this));
        return null;
    }

    @Override
    public boolean isDependentOn(String id) {
//        assert expression != null;
//        return expression.isDependentOn(id);
        return false;
    }

    @Override
    public String isDependencyOf(FormItem item) {
        assert id != null;
//        return item.isDependentOn(this.id) ? this.id : null;
        return null;
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

    public StringProperty getStringValueProperty() {
//        assert expression != null;
//        return expression.getStringValueProperty();
        return null;
    }

    @Override
    public void show(FXMLController screen) {
        screen.showComputed(this);
    }

    @Override
    public String toString() {
        return "";
    }
}
