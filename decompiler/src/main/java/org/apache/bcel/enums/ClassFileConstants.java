package org.apache.bcel.enums;

import org.apache.bcel.Const;

public enum ClassFileConstants {
    //@formatter:off
    CONSTANT_DUMMY0(0),
    CONSTANT_Utf8(1),
    CONSTANT_DUMMY1(2),
    CONSTANT_Integer(3),
    CONSTANT_Float(4),
    CONSTANT_Long(5),
    CONSTANT_Double(6),
    CONSTANT_Class(7),
    CONSTANT_Fieldref(9),
    CONSTANT_String(8),
    CONSTANT_Methodref(10),
    CONSTANT_InterfaceMethodref(11),
    CONSTANT_NameAndType(12),
    CONSTANT_DUMMY2(13),
    CONSTANT_DUMMY3(14),
    CONSTANT_MethodHandle(15),
    CONSTANT_MethodType(16),
    CONSTANT_Dynamic(17),
    CONSTANT_InvokeDynamic(18),
    CONSTANT_Module(19),
    CONSTANT_Package(20);
    //@formatter:on
    private int tag;
    private String name;

    ClassFileConstants(int tag) {
        this.tag = tag;
        this.name = Const.CONSTANT_NAMES[tag];
    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }

    public static ClassFileConstants read(byte tag) {
        for (ClassFileConstants v : ClassFileConstants.values()) {
            if (v.getTag() == tag) {
                return v;
            }
        }
        return null;
    }
}