package com.wade.decompiler.test;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Test1 implements Serializable {
    private static final long serialVersionUID = -5103281989426867172L;
    private int var1;
    private long var2;

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

    public long getVar2() {
        return var2;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public void setVar1Again(int var1) {
        int var = var1 + 3;
        this.var1 = var;
    }

    public void setVar2(long var2) {
        this.var2 = var2;
    }

    public void setVar2Again(long var1) {
        long var = var1 + 3;
        this.var2 = var;
    }
}
