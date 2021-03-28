package com.wade.decompiler.enums;

public enum ClassFileConstants {
    //@formatter:off
    CONSTANT_DUMMY0(0), CONSTANT_Utf8(1), CONSTANT_DUMMY1(2), CONSTANT_Integer(3), CONSTANT_Float(4), CONSTANT_Long(5), CONSTANT_Double(6), CONSTANT_Class(7), CONSTANT_Fieldref(9), CONSTANT_String(8), CONSTANT_Methodref(10), CONSTANT_InterfaceMethodref(11), CONSTANT_NameAndType(12), CONSTANT_DUMMY2(13), CONSTANT_DUMMY3(14), CONSTANT_MethodHandle(15), CONSTANT_MethodType(16), CONSTANT_Dynamic(17), CONSTANT_InvokeDynamic(18), CONSTANT_Module(19), CONSTANT_Package(20), CONSTANT_UNKNOWN(99);
    private String[] CONSTANT_NAMES = {"", "CONSTANT_Utf8", "", "CONSTANT_Integer", "CONSTANT_Float", "CONSTANT_Long", "CONSTANT_Double", "CONSTANT_Class", "CONSTANT_String", "CONSTANT_Fieldref", "CONSTANT_Methodref", "CONSTANT_InterfaceMethodref", "CONSTANT_NameAndType", "", "", "CONSTANT_MethodHandle", "CONSTANT_MethodType", "CONSTANT_Dynamic", "CONSTANT_InvokeDynamic", "CONSTANT_Module", "CONSTANT_Package"};
    //@formatter:on
    private byte tag;
    private String name;

    ClassFileConstants(int tag) {
        this.tag = (byte) tag;
        if (tag == 99) this.name = "Unknown";
        else this.name = CONSTANT_NAMES[tag];
    }

    public static ClassFileConstants read(byte tag) {
        for (ClassFileConstants v : ClassFileConstants.values()) {
            if (v.getTag() == tag) {
                return v;
            }
        }
        return CONSTANT_UNKNOWN;
    }

    public String getName() {
        return name;
    }

    public byte getTag() {
        return tag;
    }
}