package com.wade.decompiler.enums;

public enum ClassFileAttributes {
//@formatter:off
    ATTR_UNKNOWN(-1, "Unknown"),
    ATTR_SOURCE_FILE(0, "SourceFile"),
    ATTR_CONSTANT_VALUE(1, "ConstantValue"),
    ATTR_CODE(2, "Code"),
    ATTR_EXCEPTIONS(3, "ExceptionTable"),
    ATTR_LINE_NUMBER_TABLE(4, "LineNumberTable"),
    ATTR_LOCAL_VARIABLE_TABLE(5, "LocalVariableTable"),
    ATTR_INNER_CLASSES(6, "ATTR_INNER_CLASSES"),
    ATTR_SYNTHETIC(7, "ATTR_SYNTHETIC"),
    ATTR_DEPRECATED(8, "ATTR_DEPRECATED"),
    ATTR_PMG(9, "ATTR_PMG"),
    ATTR_SIGNATURE(10, "Signature"),
    ATTR_STACK_MAP(11, "ATTR_STACK_MAP"),
    ATTR_RUNTIME_VISIBLE_ANNOTATIONS(12, "RuntimeVisibleAnnotations"),
    ATTR_RUNTIME_INVISIBLE_ANNOTATIONS(13, "RuntimeInvisibleAnnotations"),
    ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS(14, "ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS"),
    ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS(15, "ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS"),
    ATTR_ANNOTATION_DEFAULT(16, "ATTR_ANNOTATION_DEFAULT"),
    ATTR_LOCAL_VARIABLE_TYPE_TABLE(17, "ATTR_LOCAL_VARIABLE_TYPE_TABLE"),
    ATTR_ENCLOSING_METHOD(18, "ATTR_ENCLOSING_METHOD"),
    ATTR_STACK_MAP_TABLE(19, "ATTR_STACK_MAP_TABLE"),
    ATTR_BOOTSTRAP_METHODS(20, "ATTR_BOOTSTRAP_METHODS"),
    ATTR_METHOD_PARAMETERS(21, "ATTR_METHOD_PARAMETERS"),
    ATTR_MODULE(22, "ATTR_MODULE"),
    ATTR_MODULE_PACKAGES(23, "ATTR_MODULE_PACKAGES"),
    ATTR_MODULE_MAIN_CLASS(24, "ATTR_MODULE_MAIN_CLASS"),
    ATTR_NEST_HOST(25, "ATTR_NEST_HOST"),
    ATTR_NEST_MEMBERS(26, "ATTR_NEST_MEMBERS");
//@formatter:on
    private int tag;
    private String name;

    ClassFileAttributes(int tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }
}
