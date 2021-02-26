package com.wade.decompiler.test;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Test1 implements Serializable {
    private static final long serialVersionUID = -5103281989426867172L;
    private char var1;
    private short var2;
    private int var3;
    private long var4;
    private float var5;
    private double var6;
    private boolean var7;
    private char[] var8;
    private short[] var9;
    private int[] var10;
    private long[] var11;
    private float[] var12;
    private double[] var13;
    private boolean[] var14;

    public Test1() {
        super();
    }

    public void assignInt() {
        var2 = 99;
        var3 = 99;
        var4 = 99;
        var9 = new short[6];
        var10 = new int[7];
        var11 = new long[8];
    }

    public void func1(int v1) {
        v1 = 8;
        int v2 = 9;
    }
}
