package com.wade.decompiler.decompiler;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
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
}
