package com.wade.app.util;

import java.util.Arrays;

import com.wade.app.classfile.MethodCode;

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
        return Arrays.toString(methods);
    }
}
