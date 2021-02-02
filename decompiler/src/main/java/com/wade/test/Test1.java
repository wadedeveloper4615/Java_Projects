package com.wade.test;

import java.io.Serializable;

public class Test1 implements Serializable {
    private static final long serialVersionUID = -656817334828521727L;
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
