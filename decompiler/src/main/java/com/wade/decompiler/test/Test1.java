package com.wade.decompiler.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unused", "serial" })
@MyCustomAnnotation(count = 3, books = { "C++", "Java" })
public class Test1 implements Serializable {
    private static long serialVersionUID = -656817334828521727L;
    private byte var1;
    private char var2;
    private double var3;
    private float var4;
    private int var5;
    private long var6;
    private short var7;
    private boolean var8;
    private Object var9;
    private byte[] var10;
    private char[] var11;
    private double[] var13;
    private float[] var14;
    private int[] var15;
    private long[] var16;
    private short[] var17;
    private boolean[] var18;
    private Object[] var19;
    private List<String> var20 = new ArrayList<>();

    public Test1() {
        super();
    }

    public Test1(byte var1) {
        this.var1 = var1;
    }

    public void compute(int p1, int p2) {
        this.var5 = p1 + p2;
    }

    public byte getVar1() {
        return var1;
    }

    public byte[] getVar10() {
        return var10;
    }

    public char[] getVar11() {
        return var11;
    }

    public double[] getVar13() {
        return var13;
    }

    public float[] getVar14() {
        return var14;
    }

    public int[] getVar15() {
        return var15;
    }

    public long[] getVar16() {
        return var16;
    }

    public short[] getVar17() {
        return var17;
    }

    public boolean[] getVar18() {
        return var18;
    }

    public Object[] getVar19() {
        return var19;
    }

    public char getVar2() {
        return var2;
    }

    public List<String> getVar20() {
        return var20;
    }

    public double getVar3() {
        return var3;
    }

    public float getVar4() {
        return var4;
    }

    public int getVar5() {
        return var5;
    }

    public long getVar6() {
        return var6;
    }

    public short getVar7() {
        return var7;
    }

    public Object getVar9() {
        return var9;
    }

    public boolean isVar8() {
        return var8;
    }

    public void setVar1(byte var1) {
        this.var1 = var1;
    }

    public void setVar10(byte[] var10) {
        this.var10 = var10;
    }

    public void setVar11(char[] var11) {
        this.var11 = var11;
    }

    public void setVar13(double[] var13) {
        this.var13 = var13;
    }

    public void setVar14(float[] var14) {
        this.var14 = var14;
    }

    public void setVar15(int[] var15) {
        this.var15 = var15;
    }

    public void setVar16(long[] var16) {
        this.var16 = var16;
    }

    public void setVar17(short[] var17) {
        this.var17 = var17;
    }

    public void setVar18(boolean[] var18) {
        this.var18 = var18;
    }

    public void setVar19(Object[] var19) {
        this.var19 = var19;
    }

    public void setVar2(char var2) {
        this.var2 = var2;
    }

    public void setVar20(List<String> var20) {
        this.var20 = var20;
    }

    public void setVar3(double var3) {
        this.var3 = var3;
    }

    public void setVar4(float var4) {
        this.var4 = var4;
    }

    public void setVar5(int var5) {
        this.var5 = var5;
    }

    public void setVar6(long var6) {
        this.var6 = var6;
    }

    public void setVar7(short var7) {
        this.var7 = var7;
    }

    public void setVar8(boolean var8) {
        this.var8 = var8;
    }

    public void setVar9(Object var9) {
        this.var9 = var9;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
