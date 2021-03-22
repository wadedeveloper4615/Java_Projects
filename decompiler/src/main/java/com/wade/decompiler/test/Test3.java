package com.wade.decompiler.test;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Test3 implements Serializable {
    private static final long serialVersionUID = -5103281989426867172L;
    private char[] var1;
    private byte[] var2;
    private short[] var3;
    private int[] var4;
    private long[] var5;
    private float[] var6;
    private double[] var7;
    private Object[] var8;

    public void functionA(char[] var1) {
        this.var1 = var1;
    }

    public void functionB(int index, char[] var1) {
        char var = var1[index];
        this.var1[index] = var;
    }

    public void functionC(byte[] var2) {
        this.var2 = var2;
    }

    public void functionC(int index, byte[] var2) {
        byte var = var2[index];
        this.var2[index] = var;
    }

    public void functionD(short[] var3) {
        this.var3 = var3;
    }

    public void functionE(int index, short[] var3) {
        short var = var3[index];
        this.var3[index] = var;
    }

    public void functionF(int[] var4) {
        this.var4 = var4;
    }

    public void functionG(int index, int[] var4) {
        int var = var4[index];
        this.var4[index] = var;
    }

    public void functionH(long[] var5) {
        this.var5 = var5;
    }

    public void functionI(int index, long[] var5) {
        long var = var5[index];
        this.var5[index] = var;
    }

    public void functionJ(float[] var6) {
        this.var6 = var6;
    }

    public void functionK(int index, float[] var6) {
        float var = var6[index];
        this.var6[index] = var;
    }

    public void functionL(double[] var7) {
        this.var7 = var7;
    }

    public void functionM(int index, double[] var7) {
        double var = var7[index];
        this.var7[index] = var;
    }

    public void functionN(Object[] var8) {
        this.var8 = var8;
    }

    public void functionO(int index, Object[] var8) {
        Object var = var8[index];
        this.var8[index] = var;
    }
}
