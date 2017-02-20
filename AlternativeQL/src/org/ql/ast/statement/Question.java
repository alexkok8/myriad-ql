package org.ql.ast.statement;

import com.sun.istack.internal.Nullable;
import org.ql.ast.*;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.Type;

public class Question extends Statement {
    private final Identifier id;
    private final QuestionText questionText;
    private final Type type;
    private final Expression defaultValue;

    public Question(Identifier id, QuestionText questionText, Type type, @Nullable Expression defaultValue, Metadata metadata) {
        this.id = id;
        this.questionText = questionText;
        this.type = type;
        this.defaultValue = defaultValue;
        this.metadata = metadata;
    }

    public Identifier getId() {
        return id;
    }

    public QuestionText getQuestionText() {
        return questionText;
    }

    public Type getType() {
        return type;
    }

    public Expression getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
