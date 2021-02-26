package com.wade.decompiler.decompiler;

public class Expression {
    private String variable;
    private long value;
    private ExpressionType type;

    public Expression(ExpressionType type, byte value) {
        this.type = type;
        this.value = value;
    }

    public Expression(ExpressionType type, long value) {
        this.type = type;
        this.value = value;
    }

    public Expression(ExpressionType type, String variable) {
        this.type = type;
        this.variable = variable;
    }

    public ExpressionType getType() {
        return type;
    }

    public long getValue() {
        return value;
    }

    public String getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        if (type == ExpressionType.VARIABLE) {
            return variable;
        } else if (type == ExpressionType.EXPRESSION) {
            return variable;
        } else if (type == ExpressionType.CONSTANT_NUMBER) {
            return Long.toString(value);
        } else {
            return null;
        }
    }
}
