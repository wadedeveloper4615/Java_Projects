package com.wade.app;

import com.wade.app.enums.ClassAccessFlags;

@SuppressWarnings("unused")
public class Const {
    public static int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;

    public static final short MAX_ACC_FLAG = (short) ClassAccessFlags.ACC_ENUM.getFlag();
    public static final int MAX_ACC_FLAG_I = 0x8000;

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

    public static final String ILLEGAL_TYPE = "<illegal type>";
    public static final byte T_BOOLEAN = 4;
    public static final byte T_CHAR = 5;
    public static final byte T_FLOAT = 6;
    public static final byte T_DOUBLE = 7;
    public static final byte T_BYTE = 8;
    public static final byte T_SHORT = 9;
    public static final byte T_INT = 10;
    public static final byte T_LONG = 11;
    public static final byte T_VOID = 12; // Non-standard
    public static final byte T_ARRAY = 13;
    public static final byte T_OBJECT = 14;
    public static final byte T_REFERENCE = 14; // Deprecated
    public static final byte T_UNKNOWN = 15;
    public static final byte T_ADDRESS = 16;

    private static final String[] TYPE_NAMES = { ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "boolean", "char", "float", "double", "byte", "short", "int", "long", "void", "array", "object", "unknown", "address" };
    private static final String[] SHORT_TYPE_NAMES = { ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "Z", "C", "F", "D", "B", "S", "I", "J", "V", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE };

    public static String getShortTypeName(final int index) {
        return SHORT_TYPE_NAMES[index];
    }

    public static String getTypeName(final int index) {
        return TYPE_NAMES[index];
    }
}
