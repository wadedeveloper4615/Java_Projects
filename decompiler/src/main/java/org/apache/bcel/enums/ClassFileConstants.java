package org.apache.bcel.enums;

import java.io.IOException;

public enum ClassFileConstants {
    CONSTANT_DUMMY0((byte) 0, ""),

    CONSTANT_Utf8((byte) 1, "CONSTANT_Utf8"),

    CONSTANT_DUMMY1((byte) 2, ""),

    CONSTANT_Integer((byte) 3, "CONSTANT_Integer"),

    CONSTANT_Float((byte) 4, "CONSTANT_Float"),

    CONSTANT_Long((byte) 5, "CONSTANT_Long"),

    CONSTANT_Double((byte) 6, "CONSTANT_Double"),

    CONSTANT_Class((byte) 7, "CONSTANT_Class"),

    CONSTANT_Fieldref((byte) 9, "CONSTANT_Fieldref"),

    CONSTANT_String((byte) 8, "CONSTANT_String"),

    CONSTANT_Methodref((byte) 10, "CONSTANT_Methodref"),

    CONSTANT_InterfaceMethodref((byte) 11, "CONSTANT_InterfaceMethodref"),

    CONSTANT_NameAndType((byte) 12, "CONSTANT_NameAndType"),

    CONSTANT_DUMMY2((byte) 13, ""),

    CONSTANT_DUMMY3((byte) 14, ""),

    CONSTANT_MethodHandle((byte) 15, "CONSTANT_MethodHandle"),

    CONSTANT_MethodType((byte) 16, "CONSTANT_MethodType"),

    CONSTANT_Dynamic((byte) 17, "CONSTANT_Dynamic"),

    CONSTANT_InvokeDynamic((byte) 18, "CONSTANT_InvokeDynamic"),

    CONSTANT_Module((byte) 19, "CONSTANT_Module"),

    CONSTANT_Package((byte) 20, "CONSTANT_Package"),

    CONSTANT_UNKNOWN((byte) 99, "Unknown");

    private byte tag;
    private String name;

    ClassFileConstants(byte tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public byte getTag() {
        return tag;
    }

    public static ClassFileConstants read(byte tag) throws IOException {
        for (ClassFileConstants v : ClassFileConstants.values()) {
            if (v.getTag() == tag) {
                return v;
            }
        }
        return CONSTANT_UNKNOWN;
    }
}