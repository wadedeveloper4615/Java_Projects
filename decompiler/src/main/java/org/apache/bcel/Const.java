package org.apache.bcel;

import java.util.Arrays;
import java.util.Collections;

import org.apache.bcel.enums.ClassAccessFlags;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.enums.Version;

public final class Const {
    //@formatter:off
    public static final int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;
    public static final short MAJOR = (short)Version.Version_1_1.getMajor();
    public static final short MINOR = (short)Version.Version_1_1.getMinor();
    public static final int MAX_SHORT = 65535; // 2^16 - 1
    public static final int MAX_BYTE  = 255; // 2^8 - 1
    public static final short MAX_ACC_FLAG     = 0x4000;//ACC_ENUM;
    public static final int MAX_ACC_FLAG_I = 0x8000; // ACC_MODULE is negative as a short

    // Note that do to overloading:
    // 'synchronized' is for methods, might be 'open' (if Module), 'super' (if class), or 'transitive' (if Module).
    // 'volatile'     is for fields,  might be 'bridge' (if method) or 'static_phase' (if Module)
    // 'transient'    is for fields,  might be 'varargs' (if method)
    // 'module'       is for classes, might be 'mandated' (if Module or MethodParameters)
    public static final String[] ACCESS_NAMES = {
      "public", "private", "protected", "static", "final", "synchronized",
      "volatile", "transient", "native", "interface", "abstract", "strictfp",
      "synthetic", "annotation", "enum", "module"
    };
    public static final int ACCESS_NAMES_LENGTH = ACCESS_NAMES.length;
    public static final String[] CONSTANT_NAMES = {
      "", "CONSTANT_Utf8", "", "CONSTANT_Integer",
      "CONSTANT_Float", "CONSTANT_Long", "CONSTANT_Double",
      "CONSTANT_Class", "CONSTANT_String", "CONSTANT_Fieldref",
      "CONSTANT_Methodref", "CONSTANT_InterfaceMethodref",
      "CONSTANT_NameAndType", "", "", "CONSTANT_MethodHandle",
      "CONSTANT_MethodType", "CONSTANT_Dynamic", "CONSTANT_InvokeDynamic",
      "CONSTANT_Module", "CONSTANT_Package"};

    public static final String STATIC_INITIALIZER_NAME = "<clinit>";
    public static final String CONSTRUCTOR_NAME = "<init>";
    private static final String[] INTERFACES_IMPLEMENTED_BY_ARRAYS = {"java.lang.Cloneable", "java.io.Serializable"};
    public static final int MAX_CP_ENTRIES     = 65535;
    public static final int MAX_CODE_SIZE      = 65536; //bytes
    public static final int MAX_ARRAY_DIMENSIONS = 255;
    public static final short PUSH             = 4711;
    public static final short SWITCH           = 4712;
    public static final short  UNDEFINED      = -1;
    public static final short  UNPREDICTABLE  = -2;
    public static final short  RESERVED       = -3;
    public static final String ILLEGAL_OPCODE = "<illegal opcode>";
    public static final String ILLEGAL_TYPE   = "<illegal type>";

    public static final byte T_BOOLEAN = 4;
    public static final byte T_CHAR    = 5;
    public static final byte T_FLOAT   = 6;
    public static final byte T_DOUBLE  = 7;
    public static final byte T_BYTE    = 8;
    public static final byte T_SHORT   = 9;
    public static final byte T_INT     = 10;
    public static final byte T_LONG    = 11;
    public static final byte T_VOID      = 12; // Non-standard
    public static final byte T_ARRAY     = 13;
    public static final byte T_OBJECT    = 14;
    public static final byte T_REFERENCE = 14; // Deprecated
    public static final byte T_UNKNOWN   = 15;
    public static final byte T_ADDRESS   = 16;

    private static final String[] TYPE_NAMES = {
      ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
      "boolean", "char", "float", "double", "byte", "short", "int", "long",
      "void", "array", "object", "unknown", "address"
    };

    private static final String[] CLASS_TYPE_NAMES = {
      ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
      "java.lang.Boolean", "java.lang.Character", "java.lang.Float",
      "java.lang.Double", "java.lang.Byte", "java.lang.Short",
      "java.lang.Integer", "java.lang.Long", "java.lang.Void",
      ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE,  ILLEGAL_TYPE
    };

    private static final String[] SHORT_TYPE_NAMES = {
      ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
      "Z", "C", "F", "D", "B", "S", "I", "J",
      "V", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE
    };

    public static final short[] NO_OF_OPERANDS = {
      0/*nop*/, 0/*aconst_null*/, 0/*iconst_m1*/, 0/*iconst_0*/,
      0/*iconst_1*/, 0/*iconst_2*/, 0/*iconst_3*/, 0/*iconst_4*/,
      0/*iconst_5*/, 0/*lconst_0*/, 0/*lconst_1*/, 0/*fconst_0*/,
      0/*fconst_1*/, 0/*fconst_2*/, 0/*dconst_0*/, 0/*dconst_1*/,
      1/*bipush*/, 2/*sipush*/, 1/*ldc*/, 2/*ldc_w*/, 2/*ldc2_w*/,
      1/*iload*/, 1/*lload*/, 1/*fload*/, 1/*dload*/, 1/*aload*/,
      0/*iload_0*/, 0/*iload_1*/, 0/*iload_2*/, 0/*iload_3*/,
      0/*lload_0*/, 0/*lload_1*/, 0/*lload_2*/, 0/*lload_3*/,
      0/*fload_0*/, 0/*fload_1*/, 0/*fload_2*/, 0/*fload_3*/,
      0/*dload_0*/, 0/*dload_1*/, 0/*dload_2*/, 0/*dload_3*/,
      0/*aload_0*/, 0/*aload_1*/, 0/*aload_2*/, 0/*aload_3*/,
      0/*iaload*/, 0/*laload*/, 0/*faload*/, 0/*daload*/,
      0/*aaload*/, 0/*baload*/, 0/*caload*/, 0/*saload*/,
      1/*istore*/, 1/*lstore*/, 1/*fstore*/, 1/*dstore*/,
      1/*astore*/, 0/*istore_0*/, 0/*istore_1*/, 0/*istore_2*/,
      0/*istore_3*/, 0/*lstore_0*/, 0/*lstore_1*/, 0/*lstore_2*/,
      0/*lstore_3*/, 0/*fstore_0*/, 0/*fstore_1*/, 0/*fstore_2*/,
      0/*fstore_3*/, 0/*dstore_0*/, 0/*dstore_1*/, 0/*dstore_2*/,
      0/*dstore_3*/, 0/*astore_0*/, 0/*astore_1*/, 0/*astore_2*/,
      0/*astore_3*/, 0/*iastore*/, 0/*lastore*/, 0/*fastore*/,
      0/*dastore*/, 0/*aastore*/, 0/*bastore*/, 0/*castore*/,
      0/*sastore*/, 0/*pop*/, 0/*pop2*/, 0/*dup*/, 0/*dup_x1*/,
      0/*dup_x2*/, 0/*dup2*/, 0/*dup2_x1*/, 0/*dup2_x2*/, 0/*swap*/,
      0/*iadd*/, 0/*ladd*/, 0/*fadd*/, 0/*dadd*/, 0/*isub*/,
      0/*lsub*/, 0/*fsub*/, 0/*dsub*/, 0/*imul*/, 0/*lmul*/,
      0/*fmul*/, 0/*dmul*/, 0/*idiv*/, 0/*ldiv*/, 0/*fdiv*/,
      0/*ddiv*/, 0/*irem*/, 0/*lrem*/, 0/*frem*/, 0/*drem*/,
      0/*ineg*/, 0/*lneg*/, 0/*fneg*/, 0/*dneg*/, 0/*ishl*/,
      0/*lshl*/, 0/*ishr*/, 0/*lshr*/, 0/*iushr*/, 0/*lushr*/,
      0/*iand*/, 0/*land*/, 0/*ior*/, 0/*lor*/, 0/*ixor*/, 0/*lxor*/,
      2/*iinc*/, 0/*i2l*/, 0/*i2f*/, 0/*i2d*/, 0/*l2i*/, 0/*l2f*/,
      0/*l2d*/, 0/*f2i*/, 0/*f2l*/, 0/*f2d*/, 0/*d2i*/, 0/*d2l*/,
      0/*d2f*/, 0/*i2b*/, 0/*i2c*/, 0/*i2s*/, 0/*lcmp*/, 0/*fcmpl*/,
      0/*fcmpg*/, 0/*dcmpl*/, 0/*dcmpg*/, 2/*ifeq*/, 2/*ifne*/,
      2/*iflt*/, 2/*ifge*/, 2/*ifgt*/, 2/*ifle*/, 2/*if_icmpeq*/,
      2/*if_icmpne*/, 2/*if_icmplt*/, 2/*if_icmpge*/, 2/*if_icmpgt*/,
      2/*if_icmple*/, 2/*if_acmpeq*/, 2/*if_acmpne*/, 2/*goto*/,
      2/*jsr*/, 1/*ret*/, UNPREDICTABLE/*tableswitch*/, UNPREDICTABLE/*lookupswitch*/,
      0/*ireturn*/, 0/*lreturn*/, 0/*freturn*/,
      0/*dreturn*/, 0/*areturn*/, 0/*return*/,
      2/*getstatic*/, 2/*putstatic*/, 2/*getfield*/,
      2/*putfield*/, 2/*invokevirtual*/, 2/*invokespecial*/, 2/*invokestatic*/,
      4/*invokeinterface*/, 4/*invokedynamic*/, 2/*new*/,
      1/*newarray*/, 2/*anewarray*/,
      0/*arraylength*/, 0/*athrow*/, 2/*checkcast*/,
      2/*instanceof*/, 0/*monitorenter*/,
      0/*monitorexit*/, UNPREDICTABLE/*wide*/, 3/*multianewarray*/,
      2/*ifnull*/, 2/*ifnonnull*/, 4/*goto_w*/,
      4/*jsr_w*/, 0/*breakpoint*/, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, RESERVED/*impdep1*/, RESERVED/*impdep2*/
    };

    /**
     * How the byte code operands are to be interpreted for each opcode.
     * Indexed by opcode.  TYPE_OF_OPERANDS[ILOAD] = an array of shorts
     * describing the data types for the instruction.
     */
    public static final short[][] TYPE_OF_OPERANDS = {
      {}/*nop*/, {}/*aconst_null*/, {}/*iconst_m1*/, {}/*iconst_0*/,
      {}/*iconst_1*/, {}/*iconst_2*/, {}/*iconst_3*/, {}/*iconst_4*/,
      {}/*iconst_5*/, {}/*lconst_0*/, {}/*lconst_1*/, {}/*fconst_0*/,
      {}/*fconst_1*/, {}/*fconst_2*/, {}/*dconst_0*/, {}/*dconst_1*/,
      {T_BYTE}/*bipush*/, {T_SHORT}/*sipush*/, {T_BYTE}/*ldc*/,
      {T_SHORT}/*ldc_w*/, {T_SHORT}/*ldc2_w*/,
      {T_BYTE}/*iload*/, {T_BYTE}/*lload*/, {T_BYTE}/*fload*/,
      {T_BYTE}/*dload*/, {T_BYTE}/*aload*/, {}/*iload_0*/,
      {}/*iload_1*/, {}/*iload_2*/, {}/*iload_3*/, {}/*lload_0*/,
      {}/*lload_1*/, {}/*lload_2*/, {}/*lload_3*/, {}/*fload_0*/,
      {}/*fload_1*/, {}/*fload_2*/, {}/*fload_3*/, {}/*dload_0*/,
      {}/*dload_1*/, {}/*dload_2*/, {}/*dload_3*/, {}/*aload_0*/,
      {}/*aload_1*/, {}/*aload_2*/, {}/*aload_3*/, {}/*iaload*/,
      {}/*laload*/, {}/*faload*/, {}/*daload*/, {}/*aaload*/,
      {}/*baload*/, {}/*caload*/, {}/*saload*/, {T_BYTE}/*istore*/,
      {T_BYTE}/*lstore*/, {T_BYTE}/*fstore*/, {T_BYTE}/*dstore*/,
      {T_BYTE}/*astore*/, {}/*istore_0*/, {}/*istore_1*/,
      {}/*istore_2*/, {}/*istore_3*/, {}/*lstore_0*/, {}/*lstore_1*/,
      {}/*lstore_2*/, {}/*lstore_3*/, {}/*fstore_0*/, {}/*fstore_1*/,
      {}/*fstore_2*/, {}/*fstore_3*/, {}/*dstore_0*/, {}/*dstore_1*/,
      {}/*dstore_2*/, {}/*dstore_3*/, {}/*astore_0*/, {}/*astore_1*/,
      {}/*astore_2*/, {}/*astore_3*/, {}/*iastore*/, {}/*lastore*/,
      {}/*fastore*/, {}/*dastore*/, {}/*aastore*/, {}/*bastore*/,
      {}/*castore*/, {}/*sastore*/, {}/*pop*/, {}/*pop2*/, {}/*dup*/,
      {}/*dup_x1*/, {}/*dup_x2*/, {}/*dup2*/, {}/*dup2_x1*/,
      {}/*dup2_x2*/, {}/*swap*/, {}/*iadd*/, {}/*ladd*/, {}/*fadd*/,
      {}/*dadd*/, {}/*isub*/, {}/*lsub*/, {}/*fsub*/, {}/*dsub*/,
      {}/*imul*/, {}/*lmul*/, {}/*fmul*/, {}/*dmul*/, {}/*idiv*/,
      {}/*ldiv*/, {}/*fdiv*/, {}/*ddiv*/, {}/*irem*/, {}/*lrem*/,
      {}/*frem*/, {}/*drem*/, {}/*ineg*/, {}/*lneg*/, {}/*fneg*/,
      {}/*dneg*/, {}/*ishl*/, {}/*lshl*/, {}/*ishr*/, {}/*lshr*/,
      {}/*iushr*/, {}/*lushr*/, {}/*iand*/, {}/*land*/, {}/*ior*/,
      {}/*lor*/, {}/*ixor*/, {}/*lxor*/, {T_BYTE, T_BYTE}/*iinc*/,
      {}/*i2l*/, {}/*i2f*/, {}/*i2d*/, {}/*l2i*/, {}/*l2f*/, {}/*l2d*/,
      {}/*f2i*/, {}/*f2l*/, {}/*f2d*/, {}/*d2i*/, {}/*d2l*/, {}/*d2f*/,
      {}/*i2b*/, {}/*i2c*/, {}/*i2s*/, {}/*lcmp*/, {}/*fcmpl*/,
      {}/*fcmpg*/, {}/*dcmpl*/, {}/*dcmpg*/, {T_SHORT}/*ifeq*/,
      {T_SHORT}/*ifne*/, {T_SHORT}/*iflt*/, {T_SHORT}/*ifge*/,
      {T_SHORT}/*ifgt*/, {T_SHORT}/*ifle*/, {T_SHORT}/*if_icmpeq*/,
      {T_SHORT}/*if_icmpne*/, {T_SHORT}/*if_icmplt*/,
      {T_SHORT}/*if_icmpge*/, {T_SHORT}/*if_icmpgt*/,
      {T_SHORT}/*if_icmple*/, {T_SHORT}/*if_acmpeq*/,
      {T_SHORT}/*if_acmpne*/, {T_SHORT}/*goto*/, {T_SHORT}/*jsr*/,
      {T_BYTE}/*ret*/, {}/*tableswitch*/, {}/*lookupswitch*/,
      {}/*ireturn*/, {}/*lreturn*/, {}/*freturn*/, {}/*dreturn*/,
      {}/*areturn*/, {}/*return*/, {T_SHORT}/*getstatic*/,
      {T_SHORT}/*putstatic*/, {T_SHORT}/*getfield*/,
      {T_SHORT}/*putfield*/, {T_SHORT}/*invokevirtual*/,
      {T_SHORT}/*invokespecial*/, {T_SHORT}/*invokestatic*/,
      {T_SHORT, T_BYTE, T_BYTE}/*invokeinterface*/, {T_SHORT, T_BYTE, T_BYTE}/*invokedynamic*/,
      {T_SHORT}/*new*/, {T_BYTE}/*newarray*/,
      {T_SHORT}/*anewarray*/, {}/*arraylength*/, {}/*athrow*/,
      {T_SHORT}/*checkcast*/, {T_SHORT}/*instanceof*/,
      {}/*monitorenter*/, {}/*monitorexit*/, {T_BYTE}/*wide*/,
      {T_SHORT, T_BYTE}/*multianewarray*/, {T_SHORT}/*ifnull*/,
      {T_SHORT}/*ifnonnull*/, {T_INT}/*goto_w*/, {T_INT}/*jsr_w*/,
      {}/*breakpoint*/, {}, {}, {}, {}, {}, {}, {},
      {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
      {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
      {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
      {}/*impdep1*/, {}/*impdep2*/
    };

    /**
     * Names of opcodes.  Indexed by opcode.  OPCODE_NAMES[ALOAD] = "aload".
     */
    public static final String[] OPCODE_NAMES = {
      "nop", "aconst_null", "iconst_m1", "iconst_0", "iconst_1",
      "iconst_2", "iconst_3", "iconst_4", "iconst_5", "lconst_0",
      "lconst_1", "fconst_0", "fconst_1", "fconst_2", "dconst_0",
      "dconst_1", "bipush", "sipush", "ldc", "ldc_w", "ldc2_w", "iload",
      "lload", "fload", "dload", "aload", "iload_0", "iload_1", "iload_2",
      "iload_3", "lload_0", "lload_1", "lload_2", "lload_3", "fload_0",
      "fload_1", "fload_2", "fload_3", "dload_0", "dload_1", "dload_2",
      "dload_3", "aload_0", "aload_1", "aload_2", "aload_3", "iaload",
      "laload", "faload", "daload", "aaload", "baload", "caload", "saload",
      "istore", "lstore", "fstore", "dstore", "astore", "istore_0",
      "istore_1", "istore_2", "istore_3", "lstore_0", "lstore_1",
      "lstore_2", "lstore_3", "fstore_0", "fstore_1", "fstore_2",
      "fstore_3", "dstore_0", "dstore_1", "dstore_2", "dstore_3",
      "astore_0", "astore_1", "astore_2", "astore_3", "iastore", "lastore",
      "fastore", "dastore", "aastore", "bastore", "castore", "sastore",
      "pop", "pop2", "dup", "dup_x1", "dup_x2", "dup2", "dup2_x1",
      "dup2_x2", "swap", "iadd", "ladd", "fadd", "dadd", "isub", "lsub",
      "fsub", "dsub", "imul", "lmul", "fmul", "dmul", "idiv", "ldiv",
      "fdiv", "ddiv", "irem", "lrem", "frem", "drem", "ineg", "lneg",
      "fneg", "dneg", "ishl", "lshl", "ishr", "lshr", "iushr", "lushr",
      "iand", "land", "ior", "lor", "ixor", "lxor", "iinc", "i2l", "i2f",
      "i2d", "l2i", "l2f", "l2d", "f2i", "f2l", "f2d", "d2i", "d2l", "d2f",
      "i2b", "i2c", "i2s", "lcmp", "fcmpl", "fcmpg",
      "dcmpl", "dcmpg", "ifeq", "ifne", "iflt", "ifge", "ifgt", "ifle",
      "if_icmpeq", "if_icmpne", "if_icmplt", "if_icmpge", "if_icmpgt",
      "if_icmple", "if_acmpeq", "if_acmpne", "goto", "jsr", "ret",
      "tableswitch", "lookupswitch", "ireturn", "lreturn", "freturn",
      "dreturn", "areturn", "return", "getstatic", "putstatic", "getfield",
      "putfield", "invokevirtual", "invokespecial", "invokestatic",
      "invokeinterface", "invokedynamic", "new", "newarray", "anewarray",
      "arraylength", "athrow", "checkcast", "instanceof", "monitorenter",
      "monitorexit", "wide", "multianewarray", "ifnull", "ifnonnull",
      "goto_w", "jsr_w", "breakpoint", ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
      ILLEGAL_OPCODE, "impdep1", "impdep2"
    };

    /**
     * @since 6.0
     */
    public static final int OPCODE_NAMES_LENGTH = OPCODE_NAMES.length;

    /**
     * Number of words consumed on operand stack by instructions.
     * Indexed by opcode.  CONSUME_STACK[FALOAD] = number of words
     * consumed from the stack by a faload instruction.
     */
    public static final int[] CONSUME_STACK = {
      0/*nop*/, 0/*aconst_null*/, 0/*iconst_m1*/, 0/*iconst_0*/, 0/*iconst_1*/,
      0/*iconst_2*/, 0/*iconst_3*/, 0/*iconst_4*/, 0/*iconst_5*/, 0/*lconst_0*/,
      0/*lconst_1*/, 0/*fconst_0*/, 0/*fconst_1*/, 0/*fconst_2*/, 0/*dconst_0*/,
      0/*dconst_1*/, 0/*bipush*/, 0/*sipush*/, 0/*ldc*/, 0/*ldc_w*/, 0/*ldc2_w*/, 0/*iload*/,
      0/*lload*/, 0/*fload*/, 0/*dload*/, 0/*aload*/, 0/*iload_0*/, 0/*iload_1*/, 0/*iload_2*/,
      0/*iload_3*/, 0/*lload_0*/, 0/*lload_1*/, 0/*lload_2*/, 0/*lload_3*/, 0/*fload_0*/,
      0/*fload_1*/, 0/*fload_2*/, 0/*fload_3*/, 0/*dload_0*/, 0/*dload_1*/, 0/*dload_2*/,
      0/*dload_3*/, 0/*aload_0*/, 0/*aload_1*/, 0/*aload_2*/, 0/*aload_3*/, 2/*iaload*/,
      2/*laload*/, 2/*faload*/, 2/*daload*/, 2/*aaload*/, 2/*baload*/, 2/*caload*/, 2/*saload*/,
      1/*istore*/, 2/*lstore*/, 1/*fstore*/, 2/*dstore*/, 1/*astore*/, 1/*istore_0*/,
      1/*istore_1*/, 1/*istore_2*/, 1/*istore_3*/, 2/*lstore_0*/, 2/*lstore_1*/,
      2/*lstore_2*/, 2/*lstore_3*/, 1/*fstore_0*/, 1/*fstore_1*/, 1/*fstore_2*/,
      1/*fstore_3*/, 2/*dstore_0*/, 2/*dstore_1*/, 2/*dstore_2*/, 2/*dstore_3*/,
      1/*astore_0*/, 1/*astore_1*/, 1/*astore_2*/, 1/*astore_3*/, 3/*iastore*/, 4/*lastore*/,
      3/*fastore*/, 4/*dastore*/, 3/*aastore*/, 3/*bastore*/, 3/*castore*/, 3/*sastore*/,
      1/*pop*/, 2/*pop2*/, 1/*dup*/, 2/*dup_x1*/, 3/*dup_x2*/, 2/*dup2*/, 3/*dup2_x1*/,
      4/*dup2_x2*/, 2/*swap*/, 2/*iadd*/, 4/*ladd*/, 2/*fadd*/, 4/*dadd*/, 2/*isub*/, 4/*lsub*/,
      2/*fsub*/, 4/*dsub*/, 2/*imul*/, 4/*lmul*/, 2/*fmul*/, 4/*dmul*/, 2/*idiv*/, 4/*ldiv*/,
      2/*fdiv*/, 4/*ddiv*/, 2/*irem*/, 4/*lrem*/, 2/*frem*/, 4/*drem*/, 1/*ineg*/, 2/*lneg*/,
      1/*fneg*/, 2/*dneg*/, 2/*ishl*/, 3/*lshl*/, 2/*ishr*/, 3/*lshr*/, 2/*iushr*/, 3/*lushr*/,
      2/*iand*/, 4/*land*/, 2/*ior*/, 4/*lor*/, 2/*ixor*/, 4/*lxor*/, 0/*iinc*/,
      1/*i2l*/, 1/*i2f*/, 1/*i2d*/, 2/*l2i*/, 2/*l2f*/, 2/*l2d*/, 1/*f2i*/, 1/*f2l*/,
      1/*f2d*/, 2/*d2i*/, 2/*d2l*/, 2/*d2f*/, 1/*i2b*/, 1/*i2c*/, 1/*i2s*/,
      4/*lcmp*/, 2/*fcmpl*/, 2/*fcmpg*/, 4/*dcmpl*/, 4/*dcmpg*/, 1/*ifeq*/, 1/*ifne*/,
      1/*iflt*/, 1/*ifge*/, 1/*ifgt*/, 1/*ifle*/, 2/*if_icmpeq*/, 2/*if_icmpne*/, 2/*if_icmplt*/,
      2 /*if_icmpge*/, 2/*if_icmpgt*/, 2/*if_icmple*/, 2/*if_acmpeq*/, 2/*if_acmpne*/,
      0/*goto*/, 0/*jsr*/, 0/*ret*/, 1/*tableswitch*/, 1/*lookupswitch*/, 1/*ireturn*/,
      2/*lreturn*/, 1/*freturn*/, 2/*dreturn*/, 1/*areturn*/, 0/*return*/, 0/*getstatic*/,
      UNPREDICTABLE/*putstatic*/, 1/*getfield*/, UNPREDICTABLE/*putfield*/,
      UNPREDICTABLE/*invokevirtual*/, UNPREDICTABLE/*invokespecial*/,
      UNPREDICTABLE/*invokestatic*/,
      UNPREDICTABLE/*invokeinterface*/, UNPREDICTABLE/*invokedynamic*/, 0/*new*/, 1/*newarray*/, 1/*anewarray*/,
      1/*arraylength*/, 1/*athrow*/, 1/*checkcast*/, 1/*instanceof*/, 1/*monitorenter*/,
      1/*monitorexit*/, 0/*wide*/, UNPREDICTABLE/*multianewarray*/, 1/*ifnull*/, 1/*ifnonnull*/,
      0/*goto_w*/, 0/*jsr_w*/, 0/*breakpoint*/, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNPREDICTABLE/*impdep1*/, UNPREDICTABLE/*impdep2*/
    };

    /**
     * Number of words produced onto operand stack by instructions.
     * Indexed by opcode.  CONSUME_STACK[DALOAD] = number of words
     * consumed from the stack by a daload instruction.
     */
    public static final int[] PRODUCE_STACK = {
      0/*nop*/, 1/*aconst_null*/, 1/*iconst_m1*/, 1/*iconst_0*/, 1/*iconst_1*/,
      1/*iconst_2*/, 1/*iconst_3*/, 1/*iconst_4*/, 1/*iconst_5*/, 2/*lconst_0*/,
      2/*lconst_1*/, 1/*fconst_0*/, 1/*fconst_1*/, 1/*fconst_2*/, 2/*dconst_0*/,
      2/*dconst_1*/, 1/*bipush*/, 1/*sipush*/, 1/*ldc*/, 1/*ldc_w*/, 2/*ldc2_w*/, 1/*iload*/,
      2/*lload*/, 1/*fload*/, 2/*dload*/, 1/*aload*/, 1/*iload_0*/, 1/*iload_1*/, 1/*iload_2*/,
      1/*iload_3*/, 2/*lload_0*/, 2/*lload_1*/, 2/*lload_2*/, 2/*lload_3*/, 1/*fload_0*/,
      1/*fload_1*/, 1/*fload_2*/, 1/*fload_3*/, 2/*dload_0*/, 2/*dload_1*/, 2/*dload_2*/,
      2/*dload_3*/, 1/*aload_0*/, 1/*aload_1*/, 1/*aload_2*/, 1/*aload_3*/, 1/*iaload*/,
      2/*laload*/, 1/*faload*/, 2/*daload*/, 1/*aaload*/, 1/*baload*/, 1/*caload*/, 1/*saload*/,
      0/*istore*/, 0/*lstore*/, 0/*fstore*/, 0/*dstore*/, 0/*astore*/, 0/*istore_0*/,
      0/*istore_1*/, 0/*istore_2*/, 0/*istore_3*/, 0/*lstore_0*/, 0/*lstore_1*/,
      0/*lstore_2*/, 0/*lstore_3*/, 0/*fstore_0*/, 0/*fstore_1*/, 0/*fstore_2*/,
      0/*fstore_3*/, 0/*dstore_0*/, 0/*dstore_1*/, 0/*dstore_2*/, 0/*dstore_3*/,
      0/*astore_0*/, 0/*astore_1*/, 0/*astore_2*/, 0/*astore_3*/, 0/*iastore*/, 0/*lastore*/,
      0/*fastore*/, 0/*dastore*/, 0/*aastore*/, 0/*bastore*/, 0/*castore*/, 0/*sastore*/,
      0/*pop*/, 0/*pop2*/, 2/*dup*/, 3/*dup_x1*/, 4/*dup_x2*/, 4/*dup2*/, 5/*dup2_x1*/,
      6/*dup2_x2*/, 2/*swap*/, 1/*iadd*/, 2/*ladd*/, 1/*fadd*/, 2/*dadd*/, 1/*isub*/, 2/*lsub*/,
      1/*fsub*/, 2/*dsub*/, 1/*imul*/, 2/*lmul*/, 1/*fmul*/, 2/*dmul*/, 1/*idiv*/, 2/*ldiv*/,
      1/*fdiv*/, 2/*ddiv*/, 1/*irem*/, 2/*lrem*/, 1/*frem*/, 2/*drem*/, 1/*ineg*/, 2/*lneg*/,
      1/*fneg*/, 2/*dneg*/, 1/*ishl*/, 2/*lshl*/, 1/*ishr*/, 2/*lshr*/, 1/*iushr*/, 2/*lushr*/,
      1/*iand*/, 2/*land*/, 1/*ior*/, 2/*lor*/, 1/*ixor*/, 2/*lxor*/,
      0/*iinc*/, 2/*i2l*/, 1/*i2f*/, 2/*i2d*/, 1/*l2i*/, 1/*l2f*/, 2/*l2d*/, 1/*f2i*/,
      2/*f2l*/, 2/*f2d*/, 1/*d2i*/, 2/*d2l*/, 1/*d2f*/,
      1/*i2b*/, 1/*i2c*/, 1/*i2s*/, 1/*lcmp*/, 1/*fcmpl*/, 1/*fcmpg*/,
      1/*dcmpl*/, 1/*dcmpg*/, 0/*ifeq*/, 0/*ifne*/, 0/*iflt*/, 0/*ifge*/, 0/*ifgt*/, 0/*ifle*/,
      0/*if_icmpeq*/, 0/*if_icmpne*/, 0/*if_icmplt*/, 0/*if_icmpge*/, 0/*if_icmpgt*/,
      0/*if_icmple*/, 0/*if_acmpeq*/, 0/*if_acmpne*/, 0/*goto*/, 1/*jsr*/, 0/*ret*/,
      0/*tableswitch*/, 0/*lookupswitch*/, 0/*ireturn*/, 0/*lreturn*/, 0/*freturn*/,
      0/*dreturn*/, 0/*areturn*/, 0/*return*/, UNPREDICTABLE/*getstatic*/, 0/*putstatic*/,
      UNPREDICTABLE/*getfield*/, 0/*putfield*/, UNPREDICTABLE/*invokevirtual*/,
      UNPREDICTABLE/*invokespecial*/, UNPREDICTABLE/*invokestatic*/,
      UNPREDICTABLE/*invokeinterface*/, UNPREDICTABLE/*invokedynamic*/, 1/*new*/, 1/*newarray*/, 1/*anewarray*/,
      1/*arraylength*/, 1/*athrow*/, 1/*checkcast*/, 1/*instanceof*/, 0/*monitorenter*/,
      0/*monitorexit*/, 0/*wide*/, 1/*multianewarray*/, 0/*ifnull*/, 0/*ifnonnull*/,
      0/*goto_w*/, 1/*jsr_w*/, 0/*breakpoint*/, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
      UNDEFINED, UNPREDICTABLE/*impdep1*/, UNPREDICTABLE/*impdep2*/
    };

    public static final short KNOWN_ATTRIBUTES = 27; // count of attributes
    public static final String[] ATTRIBUTE_NAMES = {
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

    public static final String[] ITEM_NAMES = {
      "Bogus", "Integer", "Float", "Double", "Long",
      "Null", "InitObject", "Object", "NewObject"
    };

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
    public static final byte REF_getField         = 1;
    public static final byte REF_getStatic        = 2;
    public static final byte REF_putField         = 3;
    public static final byte REF_putStatic        = 4;
    public static final byte REF_invokeVirtual    = 5;
    public static final byte REF_invokeStatic     = 6;
    public static final byte REF_invokeSpecial    = 7;
    public static final byte REF_newInvokeSpecial = 8;
    public static final byte REF_invokeInterface  = 9;

    private static final String[] METHODHANDLE_NAMES = {
        "",
        "getField",
        "getStatic",
        "putField",
        "putStatic",
        "invokeVirtual",
        "invokeStatic",
        "invokeSpecial",
        "newInvokeSpecial",
        "invokeInterface"
    };

    private Const() { }
    //@formatter:off

    public static String getAccessName(ClassAccessFlags flags) {
        return flags.getName();
    }

    public static String getAccessName(final int index) {
        return ACCESS_NAMES[index];
    }

    public static String getAttributeName(final int index) {
        return ATTRIBUTE_NAMES[index];
    }

    public static String getClassTypeName(final int index) {
        return CLASS_TYPE_NAMES[index];
    }

    public static String getConstantName(final int index) {
        return CONSTANT_NAMES[index];
    }

    public static int getConsumeStack(InstructionOpCodes opcode) {
        return opcode.getConsumeStack();
    }

    public static Iterable<String> getInterfacesImplementedByArrays() {
        return Collections.unmodifiableList(Arrays.asList(INTERFACES_IMPLEMENTED_BY_ARRAYS));
    }

    public static String getItemName(final int index) {
        return ITEM_NAMES[index];
    }

    public static String getMethodHandleName(final int index) {
        return METHODHANDLE_NAMES[index];
    }

    public static short getNoOfOperands(InstructionOpCodes opcode) {
        return opcode.getNumberOfOperands();
    }

    public static String getOpcodeName(InstructionOpCodes opcode) {
        return opcode.getName();
    }

    public static short getOperandType(InstructionOpCodes opcode, final int index) {
        return opcode.getTypeOfOperands()[index];
    }

    public static long getOperandTypeCount(final int opcode) {
        return TYPE_OF_OPERANDS[opcode].length;
    }

    public static int getProduceStack(InstructionOpCodes opcode) {
        return opcode.getProduceStack();
    }

    public static String getShortTypeName(final int index) {
        return SHORT_TYPE_NAMES[index];
    }

    public static String getTypeName(final int index) {
        return TYPE_NAMES[index];
    }
}
