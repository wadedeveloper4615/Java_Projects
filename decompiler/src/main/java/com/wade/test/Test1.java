package com.wade.test;

import java.io.Serializable;

@SuppressWarnings("unused")
@MyCustomAnnotation(count = 3, books = { "C++", "Java" })
public class Test1 implements Serializable {
    private static final long serialVersionUID = -656817334828521727L;
    private byte var1;
    private char var2;
    private double var3;
    private float var4;
    private int var5;
    private long var6;
    private short var7;
    private boolean var8;
    private Object var9;

    public Test1() {
        super();
    }

    public Test1(byte var1) {
        this.var1 = var1;
    }

    public void compute(int p1, int p2) {
        this.var5 = p1 + p2;
    }

    @Override
    public String toString() {
        return "Test1 [var1=" + var1 + "]";
    }
}
