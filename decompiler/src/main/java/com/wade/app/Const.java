package com.wade.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import com.wade.app.enums.ClassFileInstructions;

public final class Const {
    public static final int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;
    public static final int MAX_SHORT = 65535;
    public static final int MAX_BYTE = 255;
    public static final String STATIC_INITIALIZER_NAME = "<clinit>";
    public static final String CONSTRUCTOR_NAME = "<init>";
    private static final String[] INTERFACES_IMPLEMENTED_BY_ARRAYS = { "java.lang.Cloneable", "java.io.Serializable" };
    public static final int MAX_CP_ENTRIES = 65535;
    public static final int MAX_CODE_SIZE = 65536;
    public static final int MAX_ARRAY_DIMENSIONS = 255;
    public static final short UNDEFINED = -1;
    public static final short UNPREDICTABLE = -2;
    public static final short RESERVED = -3;

    public static final String ILLEGAL_OPCODE = "<illegal opcode>";
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
    private static final String[] CLASS_TYPE_NAMES = { ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "java.lang.Boolean", "java.lang.Character", "java.lang.Float", "java.lang.Double", "java.lang.Byte", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Void", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE };
    private static final String[] SHORT_TYPE_NAMES = { ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "Z", "C", "F", "D", "B", "S", "I", "J", "V", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE };
    public static final byte ATTR_UNKNOWN = -1;
    public static final byte ATTR_SOURCE_FILE = 0;
    public static final byte ATTR_CONSTANT_VALUE = 1;
    public static final byte ATTR_CODE = 2;
    public static final byte ATTR_EXCEPTIONS = 3;
    public static final byte ATTR_LINE_NUMBER_TABLE = 4;
    public static final byte ATTR_LOCAL_VARIABLE_TABLE = 5;
    public static final byte ATTR_INNER_CLASSES = 6;
    public static final byte ATTR_SYNTHETIC = 7;
    public static final byte ATTR_DEPRECATED = 8;
    public static final byte ATTR_PMG = 9;
    public static final byte ATTR_SIGNATURE = 10;
    public static final byte ATTR_STACK_MAP = 11;
    public static final byte ATTR_RUNTIME_VISIBLE_ANNOTATIONS = 12;
    public static final byte ATTR_RUNTIME_INVISIBLE_ANNOTATIONS = 13;
    public static final byte ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = 14;
    public static final byte ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = 15;
    public static final byte ATTR_ANNOTATION_DEFAULT = 16;
    public static final byte ATTR_LOCAL_VARIABLE_TYPE_TABLE = 17;
    public static final byte ATTR_ENCLOSING_METHOD = 18;
    public static final byte ATTR_STACK_MAP_TABLE = 19;
    public static final byte ATTR_BOOTSTRAP_METHODS = 20;
    public static final byte ATTR_METHOD_PARAMETERS = 21;
    public static final byte ATTR_MODULE = 22;
    public static final byte ATTR_MODULE_PACKAGES = 23;
    public static final byte ATTR_MODULE_MAIN_CLASS = 24;
    public static final byte ATTR_NEST_HOST = 25;
    public static final byte ATTR_NEST_MEMBERS = 26;
    public static final short KNOWN_ATTRIBUTES = 27; // count of attributes
    private static final String[] ATTRIBUTE_NAMES = { "SourceFile", "ConstantValue", "Code", "Exceptions", "LineNumberTable", "LocalVariableTable", "InnerClasses", "Synthetic", "Deprecated", "PMGClass", "Signature", "StackMap", "RuntimeVisibleAnnotations", "RuntimeInvisibleAnnotations", "RuntimeVisibleParameterAnnotations", "RuntimeInvisibleParameterAnnotations", "AnnotationDefault", "LocalVariableTypeTable", "EnclosingMethod", "StackMapTable", "BootstrapMethods", "MethodParameters", "Module", "ModulePackages", "ModuleMainClass", "NestHost", "NestMembers" };
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
    public static final byte REF_getField = 1;
    public static final byte REF_getStatic = 2;
    public static final byte REF_putField = 3;
    public static final byte REF_putStatic = 4;
    public static final byte REF_invokeVirtual = 5;
    public static final byte REF_invokeStatic = 6;
    public static final byte REF_invokeSpecial = 7;
    public static final byte REF_newInvokeSpecial = 8;
    public static final byte REF_invokeInterface = 9;
    private static final String[] METHODHANDLE_NAMES = { "", "getField", "getStatic", "putField", "putStatic", "invokeVirtual", "invokeStatic", "invokeSpecial", "newInvokeSpecial", "invokeInterface" };

    private Const() {
    }

    public static String getAttributeName(int index) {
        return ATTRIBUTE_NAMES[index];
    }

    public static String getClassTypeName(int index) {
        return CLASS_TYPE_NAMES[index];
    }

    public static int getConsumeStack(int index) throws IOException {
        return ClassFileInstructions.read((short) index).getConsumeStack();
    }

    public static Iterable<String> getInterfacesImplementedByArrays() {
        return Collections.unmodifiableList(Arrays.asList(INTERFACES_IMPLEMENTED_BY_ARRAYS));
    }

    public static String getItemName(int index) {
        return ITEM_NAMES[index];
    }

    public static String getMethodHandleName(int index) {
        return METHODHANDLE_NAMES[index];
    }

    public static short getNoOfOperands(ClassFileInstructions index) {
        return index.getNumberOfOperands();
    }

    public static String getOpcodeName(ClassFileInstructions index) {
        return index.getName();
    }

    public static short getOperandType(ClassFileInstructions opcode, int index) {
        return opcode.getTypeOfOperands()[index];
    }

    public static long getOperandTypeCount(ClassFileInstructions opcode) {
        return opcode.getTypeOfOperands().length;
    }

    public static int getProduceStack(int index) throws IOException {
        return ClassFileInstructions.read((short) index).getProduceStack();
    }

    public static String getShortTypeName(int index) {
        return SHORT_TYPE_NAMES[index];
    }

    public static String getTypeName(int index) {
        return TYPE_NAMES[index];
    }
}
