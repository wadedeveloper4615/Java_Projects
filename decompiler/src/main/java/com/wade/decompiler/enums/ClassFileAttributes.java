package com.wade.decompiler.enums;

public enum ClassFileAttributes {
    //@formatter:off
    ATTR_UNKNOWN(-1),
    ATTR_SOURCE_FILE(0),
    ATTR_CONSTANT_VALUE(1),
    ATTR_CODE(2),
    ATTR_EXCEPTIONS(3),
    ATTR_LINE_NUMBER_TABLE(4),
    ATTR_LOCAL_VARIABLE_TABLE(5),
    ATTR_INNER_CLASSES(6),
    ATTR_SYNTHETIC(7),
    ATTR_DEPRECATED(8),
    ATTR_PMG(9),
    ATTR_SIGNATURE(10),
    ATTR_STACK_MAP(11),
    ATTR_RUNTIME_VISIBLE_ANNOTATIONS(12),
    ATTR_RUNTIME_INVISIBLE_ANNOTATIONS(13),
    ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS(14),
    ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS(15),
    ATTR_ANNOTATION_DEFAULT(16),
    ATTR_LOCAL_VARIABLE_TYPE_TABLE(17),
    ATTR_ENCLOSING_METHOD(18),
    ATTR_STACK_MAP_TABLE(19),
    ATTR_BOOTSTRAP_METHODS(20),
    ATTR_METHOD_PARAMETERS(21),
    ATTR_MODULE(22),
    ATTR_MODULE_PACKAGES(23),
    ATTR_MODULE_MAIN_CLASS(24),
    ATTR_NEST_HOST(25),
    ATTR_NEST_MEMBERS(26);
    private String[] ATTRIBUTE_NAMES = {
            "SourceFile", "ConstantValue", "Code", "Exceptions",
            "LineNumberTable", "LocalVariableTable",
            "InnerClasses", "Synthetic", "Deprecated",
            "PMGClass", "Signature", "StackMap",
            "RuntimeVisibleAnnotations", "RuntimeInvisibleAnnotations",
            "RuntimeVisibleParameterAnnotations", "RuntimeInvisibleParameterAnnotations",
            "AnnotationDefault", "LocalVariableTypeTable", "EnclosingMethod", "StackMapTable",
            "BootstrapMethods", "MethodParameters", "Module", "ModulePackages",
            "ModuleMainClass", "NestHost", "NestMembers"
    };
    //@formatter:on
    private int tag;
    private String name;

    ClassFileAttributes(int tag) {
        this.tag = tag;
        if (tag==-1) {
            this.name="Unknown";
        } else {
            this.name = ATTRIBUTE_NAMES[tag];
        }
    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }

    public static ClassFileAttributes read(int flag) {
        for(ClassFileAttributes attr:ClassFileAttributes.values()) {
            if (attr.getTag()==flag) {
                return attr;
            }
        }
        return ATTR_UNKNOWN;
    }
}
