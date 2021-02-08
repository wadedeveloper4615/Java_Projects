package com.wade.app;

public class Const {
    public static int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;

    public static final int SAME_FRAME = 0;
    public static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    public static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    public static final int CHOP_FRAME = 248;
    public static final int SAME_FRAME_EXTENDED = 251;
    public static final int APPEND_FRAME = 252;
    public static final int FULL_FRAME = 255;
    public static final int SAME_FRAME_MAX = 63;
    public static final int SAME_LOCALS_1_STACK_ITEM_FRAME_MAX = 127;
    public static final int CHOP_FRAME_MAX = 250;
    public static final int APPEND_FRAME_MAX = 254;

    public static final byte ITEM_Bogus = 0;
    public static final byte ITEM_Integer = 1;
    public static final byte ITEM_Float = 2;
    public static final byte ITEM_Double = 3;
    public static final byte ITEM_Long = 4;
    public static final byte ITEM_Null = 5;
    public static final byte ITEM_InitObject = 6;
    public static final byte ITEM_Object = 7;
    public static final byte ITEM_NewObject = 8;

    private static final String[] ITEM_NAMES = { "Bogus", "Integer", "Float", "Double", "Long", "Null", "InitObject", "Object", "NewObject" };
}
