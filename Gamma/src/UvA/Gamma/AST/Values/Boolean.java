package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.Question;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.TypeChecker;

/**
 * Created by Tjarco, 14-02-17.
 */

public class Boolean extends Value {
    private boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }

    public Boolean(String value) {
        setValue(value);
    }

    @Override
    public void setValue(String value) {
        this.value = java.lang.Boolean.valueOf(value);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public boolean conformsToType(Type type) {
        return type == Type.BOOLEAN;
    }

    public boolean getValue() {
        return value;
    }


    @Override
    public void showQuestion(FXMLController screen, Question question) {
        screen.showBoolean(question);

    }
    
    @Override
    public boolean validate(String value, TypeChecker typeChecker) {
        return typeChecker.checkBool(value);
    }

    @Override
    public String computableString() {
        return toString();
    }

    @Override
    public String toString() {
        return "" + this.value;
    }

    public static boolean isBoolean(String value) {
        return value.toLowerCase().equals("true") || value.toLowerCase().equals("false");
    }
}
