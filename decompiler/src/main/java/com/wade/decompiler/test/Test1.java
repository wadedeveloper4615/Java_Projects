package com.wade.decompiler.test;

public class Test1 {
    private char var1;
    private short var2;
    private int var3;
    private long var4;

    public Test1() {
        super();
    }

    public Test1(char var1, short var2, int var3, long var4) {
        super();
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public char add1(char var) {
        return (char) (var + 5);
    }

    public int add2(int var) {
        return var - 5;
    }

    public long add3(long var) {
        return var * 5;
    }

    public short add4(short var) {
        return (short) (var / 5);
    }

    public char getVar1() {
        return var1;
    }

    public short getVar2() {
        return var2;
    }

    public int getVar3() {
        return var3;
    }

    public long getVar4() {
        return var4;
    }

    public void setVar1(char var1) {
        this.var1 = var1;
    }

    public void setVar2(short var2) {
        this.var2 = var2;
    }

    public void setVar3(int var3) {
        this.var3 = var3;
    }

    public void setVar4(long var4) {
        this.var4 = var4;
    }
}
