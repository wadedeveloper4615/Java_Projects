package com.wade.app.classfile;

import java.util.Arrays;

public class MethodsList {
    private MethodCode[] methods;

    public MethodsList(MethodCode[] methods) {
        this.methods = methods;
    }

    public MethodCode[] getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return "MethodsList [methods=" + Arrays.toString(methods) + "]";
    }
}
