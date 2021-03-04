package com.wade.decompiler.test;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Test1 implements Serializable {
    private static final long serialVersionUID = -5103281989426867172L;
    private int var1;

    public Test1() {
        super();
    }

    public Test1(int var1) {
        super();
        this.var1 = var1;
    }

    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }
}
