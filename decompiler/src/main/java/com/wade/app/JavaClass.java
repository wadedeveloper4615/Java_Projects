package com.wade.app;

import com.wade.app.constantpool.ConstantPool;

public class JavaClass {
    public static final byte HEAP = 1;
    public static final byte FILE = 2;
    public static final byte ZIP = 3;

    public JavaClass(int classNameIndex, int superclassNameIndex, String fileName, int major, int minor, int accessFlags, ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, byte b) {
    }
}
