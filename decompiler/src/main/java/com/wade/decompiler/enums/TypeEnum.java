package com.wade.decompiler.enums;

public enum TypeEnum {
    //@formatter:off
    T_BOOLEAN(4), T_CHAR(5), T_FLOAT(6), T_DOUBLE(7), T_BYTE(8), T_SHORT(9), T_INTEGER(10), T_LONG(11), T_VOID(12), T_ARRAY(13), T_OBJECT(14), T_REFERENCE(14), T_UNKNOWN(15), T_ADDRESS(16), T_NONE(17);
    //@formatter:off
    private final int tag;
    private final String typeName;
    private final String classTypeName;
    private final String shortTypeName;
    public String ILLEGAL_TYPE = "<illegal type>";
    private final String[] TYPE_NAMES = {ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "boolean", "char", "float", "double", "byte", "short", "int", "long", "void", "array", "object", "unknown", "address", ILLEGAL_TYPE};
    private final String[] CLASS_TYPE_NAMES = {ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "java.lang.Boolean", "java.lang.Character", "java.lang.Float", "java.lang.Double", "java.lang.Byte", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Void", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE};
    private final String[] SHORT_TYPE_NAMES = {ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "Z", "C", "F", "D", "B", "S", "I", "J", "V", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE};

    TypeEnum(int tag) {
        this.tag = tag;
        this.typeName = TYPE_NAMES[tag];
        this.classTypeName = CLASS_TYPE_NAMES[tag];
        this.shortTypeName = SHORT_TYPE_NAMES[tag];
    }

    public static TypeEnum read(int flag) {
        for (TypeEnum attr : TypeEnum.values()) {
            if (attr.getTag() == flag) {
                return attr;
            }
        }
        return T_UNKNOWN;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public String getShortTypeName() {
        return shortTypeName;
    }

    public int getTag() {
        return tag;
    }

    public String getTypeName() {
        return typeName;
    }
}
