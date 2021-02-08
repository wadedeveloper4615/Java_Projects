package com.wade.app.util;

import java.util.Arrays;

import com.wade.app.classfile.FieldVariable;

public class FieldsList {
    private FieldVariable[] fields;

    public FieldsList(FieldVariable[] fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "FieldsList [fields=" + Arrays.toString(fields) + "]";
    }
}
