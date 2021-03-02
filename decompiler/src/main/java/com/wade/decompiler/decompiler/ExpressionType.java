package com.wade.decompiler.decompiler;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true, includeFieldNames = true)
public enum ExpressionType {
    VARIABLE, EXPRESSION, CONSTANT_NUMBER;
}
