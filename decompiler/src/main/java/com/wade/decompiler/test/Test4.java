package com.wade.decompiler.test;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Test4 implements Serializable {
    private static final long serialVersionUID = -5103281989426867172L;
    private int var1;
    private int var2;

    public void function1(int var) {
        if (var == 10) {
            this.var1 = var + 5;
        }
        this.var2 = var + 7;
    }

    public void function2(int var) {
        if (var == 10) {
            this.var1 = var + 5;
        } else {
            this.var2 = var + 7;
        }
    }
}
