package com.wade.decompiler.data.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings({"unused"})
public class SimpleClassWithConstantPool {
    private char var1;
    private short var2;
    private int var3 = 250000;
    private long var4 = 7;
    private float var5 = 8f;
    private double var6 = 6;
    private boolean var7 = true;
    private List<String> v8;
    private String v9 = "shdhsalhd";

    public SimpleClassWithConstantPool() {
        super();
        v8 = new ArrayList<>();
        v8.add("");
        this.toString();
    }

    public void fuction1() {
        List<String> value = List.of("Red", "Green", "Blue");
        Stream<String> stream = value.stream();
        long lengthyColors = stream.filter(c -> c.length() > 3).count();
    }
}
