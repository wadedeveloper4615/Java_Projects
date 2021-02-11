package org.apache.bcel.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
}
