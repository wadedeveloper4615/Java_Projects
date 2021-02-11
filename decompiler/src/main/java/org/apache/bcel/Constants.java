
package org.apache.bcel;

@Deprecated
public interface Constants {

    short MAJOR_1_1 = 45;

    short MINOR_1_1 = 3;

    short MAJOR_1_2 = 46;

    short MINOR_1_2 = 0;

    short MAJOR_1_3 = 47;

    short MINOR_1_3 = 0;

    short MAJOR_1_4 = 48;

    short MINOR_1_4 = 0;

    short MAJOR_1_5 = 49;

    short MINOR_1_5 = 0;

    short MAJOR = MAJOR_1_1;

    short MINOR = MINOR_1_1;

    int MAX_SHORT = 65535; // 2^16 - 1

    int MAX_BYTE = 255; // 2^8 - 1

    short ACC_PUBLIC = 0x0001;

    short ACC_PRIVATE = 0x0002;

    short ACC_PROTECTED = 0x0004;

    short ACC_STATIC = 0x0008;

    short ACC_FINAL = 0x0010;

    short ACC_SYNCHRONIZED = 0x0020;

    short ACC_VOLATILE = 0x0040;

    short ACC_BRIDGE = 0x0040;

    short ACC_TRANSIENT = 0x0080;

    short ACC_VARARGS = 0x0080;

    short ACC_NATIVE = 0x0100;

    short ACC_INTERFACE = 0x0200;

    short ACC_ABSTRACT = 0x0400;

    short ACC_STRICT = 0x0800;

    short ACC_SYNTHETIC = 0x1000;

    short ACC_ANNOTATION = 0x2000;

    short ACC_ENUM = 0x4000;

    // Applies to classes compiled by new compilers only

    short ACC_SUPER = 0x0020;

    short MAX_ACC_FLAG = ACC_ENUM;

    String[] ACCESS_NAMES = { "public", "private", "protected", "static", "final", "synchronized", "volatile", "transient", "native", "interface", "abstract", "strictfp", "synthetic", "annotation", "enum" };

    byte CONSTANT_Utf8 = 1;

    byte CONSTANT_Integer = 3;

    byte CONSTANT_Float = 4;

    byte CONSTANT_Long = 5;

    byte CONSTANT_Double = 6;

    byte CONSTANT_Class = 7;

    byte CONSTANT_Fieldref = 9;

    byte CONSTANT_String = 8;

    byte CONSTANT_Methodref = 10;

    byte CONSTANT_InterfaceMethodref = 11;

    byte CONSTANT_NameAndType = 12;

    String[] CONSTANT_NAMES = { "", "CONSTANT_Utf8", "", "CONSTANT_Integer", "CONSTANT_Float", "CONSTANT_Long", "CONSTANT_Double", "CONSTANT_Class", "CONSTANT_String", "CONSTANT_Fieldref", "CONSTANT_Methodref", "CONSTANT_InterfaceMethodref", "CONSTANT_NameAndType" };

    String STATIC_INITIALIZER_NAME = "<clinit>";

    String CONSTRUCTOR_NAME = "<init>";

    String[] INTERFACES_IMPLEMENTED_BY_ARRAYS = { "java.lang.Cloneable", "java.io.Serializable" };

    int MAX_CP_ENTRIES = 65535;

    int MAX_CODE_SIZE = 65536; // bytes

    short NOP = 0;

    short ACONST_NULL = 1;

    short ICONST_M1 = 2;

    short ICONST_0 = 3;

    short ICONST_1 = 4;

    short ICONST_2 = 5;

    short ICONST_3 = 6;

    short ICONST_4 = 7;

    short ICONST_5 = 8;

    short LCONST_0 = 9;

    short LCONST_1 = 10;

    short FCONST_0 = 11;

    short FCONST_1 = 12;

    short FCONST_2 = 13;

    short DCONST_0 = 14;

    short DCONST_1 = 15;

    short BIPUSH = 16;

    short SIPUSH = 17;

    short LDC = 18;

    short LDC_W = 19;

    short LDC2_W = 20;

    short ILOAD = 21;

    short LLOAD = 22;

    short FLOAD = 23;

    short DLOAD = 24;

    short ALOAD = 25;

    short ILOAD_0 = 26;

    short ILOAD_1 = 27;

    short ILOAD_2 = 28;

    short ILOAD_3 = 29;

    short LLOAD_0 = 30;

    short LLOAD_1 = 31;

    short LLOAD_2 = 32;

    short LLOAD_3 = 33;

    short FLOAD_0 = 34;

    short FLOAD_1 = 35;

    short FLOAD_2 = 36;

    short FLOAD_3 = 37;

    short DLOAD_0 = 38;

    short DLOAD_1 = 39;

    short DLOAD_2 = 40;

    short DLOAD_3 = 41;

    short ALOAD_0 = 42;

    short ALOAD_1 = 43;

    short ALOAD_2 = 44;

    short ALOAD_3 = 45;

    short IALOAD = 46;

    short LALOAD = 47;

    short FALOAD = 48;

    short DALOAD = 49;

    short AALOAD = 50;

    short BALOAD = 51;

    short CALOAD = 52;

    short SALOAD = 53;

    short ISTORE = 54;

    short LSTORE = 55;

    short FSTORE = 56;

    short DSTORE = 57;

    short ASTORE = 58;

    short ISTORE_0 = 59;

    short ISTORE_1 = 60;

    short ISTORE_2 = 61;

    short ISTORE_3 = 62;

    short LSTORE_0 = 63;

    short LSTORE_1 = 64;

    short LSTORE_2 = 65;

    short LSTORE_3 = 66;

    short FSTORE_0 = 67;

    short FSTORE_1 = 68;

    short FSTORE_2 = 69;

    short FSTORE_3 = 70;

    short DSTORE_0 = 71;

    short DSTORE_1 = 72;

    short DSTORE_2 = 73;

    short DSTORE_3 = 74;

    short ASTORE_0 = 75;

    short ASTORE_1 = 76;

    short ASTORE_2 = 77;

    short ASTORE_3 = 78;

    short IASTORE = 79;

    short LASTORE = 80;

    short FASTORE = 81;

    short DASTORE = 82;

    short AASTORE = 83;

    short BASTORE = 84;

    short CASTORE = 85;

    short SASTORE = 86;

    short POP = 87;

    short POP2 = 88;

    short DUP = 89;

    short DUP_X1 = 90;

    short DUP_X2 = 91;

    short DUP2 = 92;

    short DUP2_X1 = 93;

    short DUP2_X2 = 94;

    short SWAP = 95;

    short IADD = 96;

    short LADD = 97;

    short FADD = 98;

    short DADD = 99;

    short ISUB = 100;

    short LSUB = 101;

    short FSUB = 102;

    short DSUB = 103;

    short IMUL = 104;

    short LMUL = 105;

    short FMUL = 106;

    short DMUL = 107;

    short IDIV = 108;

    short LDIV = 109;

    short FDIV = 110;

    short DDIV = 111;

    short IREM = 112;

    short LREM = 113;

    short FREM = 114;

    short DREM = 115;

    short INEG = 116;

    short LNEG = 117;

    short FNEG = 118;

    short DNEG = 119;

    short ISHL = 120;

    short LSHL = 121;

    short ISHR = 122;

    short LSHR = 123;

    short IUSHR = 124;

    short LUSHR = 125;

    short IAND = 126;

    short LAND = 127;

    short IOR = 128;

    short LOR = 129;

    short IXOR = 130;

    short LXOR = 131;

    short IINC = 132;

    short I2L = 133;

    short I2F = 134;

    short I2D = 135;

    short L2I = 136;

    short L2F = 137;

    short L2D = 138;

    short F2I = 139;

    short F2L = 140;

    short F2D = 141;

    short D2I = 142;

    short D2L = 143;

    short D2F = 144;

    short I2B = 145;

    short INT2BYTE = 145; // Old notion

    short I2C = 146;

    short INT2CHAR = 146; // Old notion

    short I2S = 147;

    short INT2SHORT = 147; // Old notion

    short LCMP = 148;

    short FCMPL = 149;

    short FCMPG = 150;

    short DCMPL = 151;

    short DCMPG = 152;

    short IFEQ = 153;

    short IFNE = 154;

    short IFLT = 155;

    short IFGE = 156;

    short IFGT = 157;

    short IFLE = 158;

    short IF_ICMPEQ = 159;

    short IF_ICMPNE = 160;

    short IF_ICMPLT = 161;

    short IF_ICMPGE = 162;

    short IF_ICMPGT = 163;

    short IF_ICMPLE = 164;

    short IF_ACMPEQ = 165;

    short IF_ACMPNE = 166;

    short GOTO = 167;

    short JSR = 168;

    short RET = 169;

    short TABLESWITCH = 170;

    short LOOKUPSWITCH = 171;

    short IRETURN = 172;

    short LRETURN = 173;

    short FRETURN = 174;

    short DRETURN = 175;

    short ARETURN = 176;

    short RETURN = 177;

    short GETSTATIC = 178;

    short PUTSTATIC = 179;

    short GETFIELD = 180;

    short PUTFIELD = 181;

    short INVOKEVIRTUAL = 182;

    short INVOKESPECIAL = 183;

    short INVOKENONVIRTUAL = 183; // Old name in JDK 1.0

    short INVOKESTATIC = 184;

    short INVOKEINTERFACE = 185;

    short INVOKEDYNAMIC = 186;

    short NEW = 187;

    short NEWARRAY = 188;

    short ANEWARRAY = 189;

    short ARRAYLENGTH = 190;

    short ATHROW = 191;

    short CHECKCAST = 192;

    short INSTANCEOF = 193;

    short MONITORENTER = 194;

    short MONITOREXIT = 195;

    short WIDE = 196;

    short MULTIANEWARRAY = 197;

    short IFNULL = 198;

    short IFNONNULL = 199;

    short GOTO_W = 200;

    short JSR_W = 201;

    short BREAKPOINT = 202;

    short LDC_QUICK = 203;

    short LDC_W_QUICK = 204;

    short LDC2_W_QUICK = 205;

    short GETFIELD_QUICK = 206;

    short PUTFIELD_QUICK = 207;

    short GETFIELD2_QUICK = 208;

    short PUTFIELD2_QUICK = 209;

    short GETSTATIC_QUICK = 210;

    short PUTSTATIC_QUICK = 211;

    short GETSTATIC2_QUICK = 212;

    short PUTSTATIC2_QUICK = 213;

    short INVOKEVIRTUAL_QUICK = 214;

    short INVOKENONVIRTUAL_QUICK = 215;

    short INVOKESUPER_QUICK = 216;

    short INVOKESTATIC_QUICK = 217;

    short INVOKEINTERFACE_QUICK = 218;

    short INVOKEVIRTUALOBJECT_QUICK = 219;

    short NEW_QUICK = 221;

    short ANEWARRAY_QUICK = 222;

    short MULTIANEWARRAY_QUICK = 223;

    short CHECKCAST_QUICK = 224;

    short INSTANCEOF_QUICK = 225;

    short INVOKEVIRTUAL_QUICK_W = 226;

    short GETFIELD_QUICK_W = 227;

    short PUTFIELD_QUICK_W = 228;

    short IMPDEP1 = 254;

    short IMPDEP2 = 255;

    short PUSH = 4711;

    short SWITCH = 4712;

    short UNDEFINED = -1;

    short UNPREDICTABLE = -2;

    short RESERVED = -3;

    String ILLEGAL_OPCODE = "<illegal opcode>";

    String ILLEGAL_TYPE = "<illegal type>";

    byte T_BOOLEAN = 4;

    byte T_CHAR = 5;

    byte T_FLOAT = 6;

    byte T_DOUBLE = 7;

    byte T_BYTE = 8;

    byte T_SHORT = 9;

    byte T_INT = 10;

    byte T_LONG = 11;

    byte T_VOID = 12; // Non-standard

    byte T_ARRAY = 13;

    byte T_OBJECT = 14;

    byte T_REFERENCE = 14; // Deprecated

    byte T_UNKNOWN = 15;

    byte T_ADDRESS = 16;

    String[] TYPE_NAMES = { ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "boolean", "char", "float", "double", "byte", "short", "int", "long", "void", "array", "object", "unknown", "address" };

    String[] CLASS_TYPE_NAMES = { ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "java.lang.Boolean", "java.lang.Character", "java.lang.Float", "java.lang.Double", "java.lang.Byte", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Void", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE };

    String[] SHORT_TYPE_NAMES = { ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE, "Z", "C", "F", "D", "B", "S", "I", "J", "V", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE };

    short[] NO_OF_OPERANDS = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, UNPREDICTABLE, UNPREDICTABLE, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 4, 4, 2, 1, 2, 0, 0, 2, 2, 0, 0, UNPREDICTABLE, 3, 2, 2, 4, 4, 0, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, RESERVED, RESERVED };

    short[][] TYPE_OF_OPERANDS = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { T_BYTE }, { T_SHORT }, { T_BYTE }, { T_SHORT }, { T_SHORT }, { T_BYTE }, { T_BYTE }, { T_BYTE }, { T_BYTE }, { T_BYTE }, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { T_BYTE }, { T_BYTE }, { T_BYTE }, { T_BYTE }, { T_BYTE }, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { T_BYTE, T_BYTE }, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_BYTE }, {}, {}, {}, {}, {}, {}, {}, {}, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT }, { T_SHORT, T_BYTE, T_BYTE }, { T_SHORT, T_BYTE, T_BYTE }, { T_SHORT }, { T_BYTE }, { T_SHORT }, {}, {}, { T_SHORT }, { T_SHORT }, {}, {}, { T_BYTE }, { T_SHORT, T_BYTE }, { T_SHORT }, { T_SHORT }, { T_INT }, { T_INT }, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {} };

    String[] OPCODE_NAMES = { "nop", "aconst_null", "iconst_m1", "iconst_0", "iconst_1", "iconst_2", "iconst_3", "iconst_4", "iconst_5", "lconst_0", "lconst_1", "fconst_0", "fconst_1", "fconst_2", "dconst_0", "dconst_1", "bipush", "sipush", "ldc", "ldc_w", "ldc2_w", "iload", "lload", "fload", "dload", "aload", "iload_0", "iload_1", "iload_2", "iload_3", "lload_0", "lload_1", "lload_2", "lload_3", "fload_0", "fload_1", "fload_2", "fload_3", "dload_0", "dload_1", "dload_2", "dload_3", "aload_0", "aload_1", "aload_2", "aload_3", "iaload", "laload", "faload", "daload", "aaload", "baload", "caload", "saload", "istore", "lstore", "fstore", "dstore", "astore", "istore_0", "istore_1", "istore_2", "istore_3", "lstore_0", "lstore_1", "lstore_2", "lstore_3", "fstore_0", "fstore_1", "fstore_2", "fstore_3", "dstore_0", "dstore_1", "dstore_2", "dstore_3", "astore_0", "astore_1", "astore_2", "astore_3", "iastore", "lastore", "fastore", "dastore", "aastore", "bastore", "castore", "sastore", "pop", "pop2", "dup", "dup_x1", "dup_x2", "dup2", "dup2_x1", "dup2_x2", "swap", "iadd", "ladd", "fadd", "dadd", "isub", "lsub", "fsub", "dsub", "imul", "lmul", "fmul", "dmul", "idiv", "ldiv", "fdiv", "ddiv", "irem", "lrem", "frem", "drem", "ineg", "lneg", "fneg", "dneg", "ishl", "lshl", "ishr", "lshr", "iushr", "lushr", "iand", "land", "ior", "lor", "ixor", "lxor", "iinc", "i2l", "i2f", "i2d", "l2i", "l2f", "l2d", "f2i", "f2l", "f2d", "d2i", "d2l", "d2f", "i2b", "i2c", "i2s", "lcmp", "fcmpl", "fcmpg", "dcmpl", "dcmpg", "ifeq", "ifne", "iflt", "ifge", "ifgt", "ifle", "if_icmpeq", "if_icmpne", "if_icmplt", "if_icmpge", "if_icmpgt", "if_icmple", "if_acmpeq", "if_acmpne", "goto", "jsr", "ret", "tableswitch", "lookupswitch", "ireturn", "lreturn", "freturn", "dreturn", "areturn", "return", "getstatic", "putstatic", "getfield", "putfield", "invokevirtual", "invokespecial", "invokestatic", "invokeinterface", "invokedynamic", "new", "newarray", "anewarray", "arraylength", "athrow", "checkcast", "instanceof", "monitorenter", "monitorexit", "wide", "multianewarray", "ifnull", "ifnonnull", "goto_w", "jsr_w", "breakpoint", ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, "impdep1", "impdep2" };

    int[] CONSUME_STACK = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 3, 4, 3, 4, 3, 3, 3, 3, 1, 2, 1, 2, 3, 2, 3, 4, 2, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 1, 2, 1, 2, 2, 3, 2, 3, 2, 3, 2, 4, 2, 4, 2, 4, 0, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4, 2, 2, 4, 4, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 1, 2, 1, 0, 0, UNPREDICTABLE, 1, UNPREDICTABLE, UNPREDICTABLE, UNPREDICTABLE, UNPREDICTABLE, UNPREDICTABLE, UNPREDICTABLE, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, UNPREDICTABLE, 1, 1, 0, 0, 0, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNPREDICTABLE, UNPREDICTABLE };

    int[] PRODUCE_STACK = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 4, 5, 6, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, UNPREDICTABLE, 0, UNPREDICTABLE, 0, UNPREDICTABLE, UNPREDICTABLE, UNPREDICTABLE, UNPREDICTABLE, UNPREDICTABLE, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNPREDICTABLE, UNPREDICTABLE };

    byte ATTR_UNKNOWN = -1;
    byte ATTR_SOURCE_FILE = 0;
    byte ATTR_CONSTANT_VALUE = 1;
    byte ATTR_CODE = 2;
    byte ATTR_EXCEPTIONS = 3;
    byte ATTR_LINE_NUMBER_TABLE = 4;
    byte ATTR_LOCAL_VARIABLE_TABLE = 5;
    byte ATTR_INNER_CLASSES = 6;
    byte ATTR_SYNTHETIC = 7;
    byte ATTR_DEPRECATED = 8;
    byte ATTR_PMG = 9;
    byte ATTR_SIGNATURE = 10;
    byte ATTR_STACK_MAP = 11;
    byte ATTR_RUNTIMEVISIBLE_ANNOTATIONS = 12;
    byte ATTR_RUNTIMEINVISIBLE_ANNOTATIONS = 13;
    byte ATTR_RUNTIMEVISIBLE_PARAMETER_ANNOTATIONS = 14;
    byte ATTR_RUNTIMEINVISIBLE_PARAMETER_ANNOTATIONS = 15;
    byte ATTR_ANNOTATION_DEFAULT = 16;

    short KNOWN_ATTRIBUTES = 12;// should be 17

    // TODO: mutable public array!!
    String[] ATTRIBUTE_NAMES = { "SourceFile", "ConstantValue", "Code", "Exceptions", "LineNumberTable", "LocalVariableTable", "InnerClasses", "Synthetic", "Deprecated", "PMGClass", "Signature", "StackMap", "RuntimeVisibleAnnotations", "RuntimeInvisibleAnnotations", "RuntimeVisibleParameterAnnotations", "RuntimeInvisibleParameterAnnotations", "AnnotationDefault" };

    byte ITEM_Bogus = 0;
    byte ITEM_Integer = 1;
    byte ITEM_Float = 2;
    byte ITEM_Double = 3;
    byte ITEM_Long = 4;
    byte ITEM_Null = 5;
    byte ITEM_InitObject = 6;
    byte ITEM_Object = 7;
    byte ITEM_NewObject = 8;

    String[] ITEM_NAMES = { "Bogus", "Integer", "Float", "Double", "Long", "Null", "InitObject", "Object", "NewObject" };

}
