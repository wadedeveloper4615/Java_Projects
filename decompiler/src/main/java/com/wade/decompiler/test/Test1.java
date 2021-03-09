package com.wade.decompiler.test;

@SuppressWarnings("unused")
public class Test1 {// implements Serializable {
    private static final long serialVersionUID = -5103281989426867172L;
    private char var1;
    private byte var2;
    private short var3;
    private int var4;
    private long var5;
    private float var6;
    private double var7;
    private Object var8;

    public void function1(char var) {
        char var1 = (char) (var + 2);
        this.var1 = var1;
    }

    public void function2(byte var) {
        byte var1 = (byte) (var + 2);
        this.var2 = var1;
    }

    public void function3(short var) {
        short var1 = (short) (var + 2);
        this.var3 = var1;
    }

    public void function4(int var) {
        int var1 = var + 2;
        this.var4 = var1;
    }

    public void function5(long var) {
        long var2 = var + 2;
        this.var5 = var2;
    }

    public void function6(float var) {
        float var1 = var + 2;
        this.var6 = var1;
    }

    public void function7(double var) {
        double var1 = var + 2;
        this.var7 = var1;
    }

    public void function8(Object var) {
        this.var8 = var;
    }

    public void function9(int var) {
        int var1 = var + 2500000;
        this.var4 = var1;
    }
}
