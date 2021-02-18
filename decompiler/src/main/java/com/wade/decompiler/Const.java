package com.wade.decompiler;

import java.util.Arrays;
import java.util.Collections;

import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.enums.Version;

public class Const {
    //@formatter:off
	public static  int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;
	//	public static  final short MAJOR_1_1 = 45;
	//	public static  final short MINOR_1_1 = 3;
	//	public static  final short MAJOR_1_2 = 46;
	//	public static  final short MINOR_1_2 = 0;
	//	public static  final short MAJOR_1_3 = 47;
	//	public static  final short MINOR_1_3 = 0;
	//	public static  final short MAJOR_1_4 = 48;
	//	public static  final short MINOR_1_4 = 0;
	//	public static  final short MAJOR_1_5 = 49;
	//	public static  final short MINOR_1_5 = 0;
	//	public static  final short MAJOR_1_6 = 50;
	//	public static  final short MINOR_1_6 = 0;
	//	public static  final short MAJOR_1_7 = 51;
	//	public static  final short MINOR_1_7 = 0;
	//	public static  final short MAJOR_1_8 = 52;
	//	public static  final short MINOR_1_8 = 0;
	//	public static  final short MAJOR_9 = 53;
	//	public static  final short MINOR_9 = 0;
	//	public static  final short MAJOR_10 = 54;
	//	public static  final short MINOR_10 = 0;
	//	public static  final short MAJOR_11 = 55;
	//	public static  final short MINOR_11 = 0;
	//	public static  final short MAJOR_12 = 56;
	//	public static  final short MINOR_12 = 0;
	//	public static  final short MAJOR_13 = 57;
	//	public static  final short MINOR_13 = 0;
	//	public static  final short MAJOR_14 = 58;
	//	public static  final short MINOR_14 = 0;

	public static  final short MAJOR = (short) Version.Version_1_1.getMajor();
	public static  final short MINOR = (short) Version.Version_1_1.getMinor();

	public static  int MAX_SHORT = 65535; // 2^16 - 1
	public static  int MAX_BYTE  = 255; // 2^8 - 1

//	public static  final short ACC_PUBLIC       = 0x0001;
//	public static  final short ACC_PRIVATE      = 0x0002;
//	public static  final short ACC_PROTECTED    = 0x0004;
//	public static  final short ACC_STATIC       = 0x0008;
//	public static  final short ACC_        = 0x0010;
//	public static  final short ACC_OPEN         = 0x0020;
//	public static  final short ACC_SUPER        = 0x0020;
//	public static  final short ACC_SYNCHRONIZED = 0x0020;
//	public static  final short ACC_TRANSITIVE   = 0x0020;
//	public static  final short ACC_BRIDGE       = 0x0040;
//	public static  final short ACC_STATIC_PHASE = 0x0040;
//	public static  final short ACC_VOLATILE     = 0x0040;
//	public static  final short ACC_TRANSIENT    = 0x0080;
//	public static  final short ACC_VARARGS      = 0x0080;
//	public static  final short ACC_NATIVE       = 0x0100;
//	public static  final short ACC_INTERFACE    = 0x0200;
//	public static  final short ACC_ABSTRACT     = 0x0400;
//	public static  final short ACC_STRICT       = 0x0800;
//	public static  final short ACC_SYNTHETIC    = 0x1000;
//	public static  final short ACC_ANNOTATION   = 0x2000;
//	public static  final short ACC_ENUM         = 0x4000;
//	public static  final short ACC_MANDATED     = (short) 0x8000;
//	public static  final short ACC_MODULE       = (short) 0x8000;

	public static  final short MAX_ACC_FLAG     = (short) ClassAccessFlags.ACC_ENUM.getFlag();
	public static  int MAX_ACC_FLAG_I     = 0x8000; // ACC_MODULE is negative as a short

	// Note that do to overloading:
	// 'synchronized' is for methods, might be 'open' (if Module), 'super' (if class), or 'transitive' (if Module).
	// 'volatile'     is for fields,  might be 'bridge' (if method) or 'static_phase' (if Module)
	// 'transient'    is for fields,  might be 'varargs' (if method)
	// 'module'       is for classes, might be 'mandated' (if Module or MethodParameters)
	/**
	 * The names of the access flags.
	 */
	private static  String[] ACCESS_NAMES = {
			"public", "private", "protected", "static", "", "synchronized",
			"volatile", "transient", "native", "interface", "abstract", "strictfp",
			"synthetic", "annotation", "enum", "module"
	};

	public static  int ACCESS_NAMES_LENGTH = ACCESS_NAMES.length;

//	public static  byte CONSTANT_Utf8               = 1;
//	public static  byte CONSTANT_Integer            = 3;
//	public static  byte CONSTANT_Float              = 4;
//	public static  byte CONSTANT_Long               = 5;
//	public static  byte CONSTANT_Double             = 6;
//	public static  byte CONSTANT_Class              = 7;
//	public static  byte CONSTANT_Fieldref           = 9;
//	public static  byte CONSTANT_String             = 8;
//	public static  byte CONSTANT_Methodref          = 10;
//	public static  byte CONSTANT_InterfaceMethodref = 11;
//	public static  byte CONSTANT_NameAndType        = 12;
//	public static  byte CONSTANT_MethodHandle       = 15;
//	public static  byte CONSTANT_MethodType         = 16;
//	public static  byte CONSTANT_Dynamic            = 17;
//	public static  byte CONSTANT_InvokeDynamic      = 18;
//	public static  byte CONSTANT_Module             = 19;
//	public static  byte CONSTANT_Package            = 20;
	private static  String[] CONSTANT_NAMES = {
			"", "CONSTANT_Utf8", "", "CONSTANT_Integer",
			"CONSTANT_Float", "CONSTANT_Long", "CONSTANT_Double",
			"CONSTANT_Class", "CONSTANT_String", "CONSTANT_Fieldref",
			"CONSTANT_Methodref", "CONSTANT_InterfaceMethodref",
			"CONSTANT_NameAndType", "", "", "CONSTANT_MethodHandle",
			"CONSTANT_MethodType", "CONSTANT_Dynamic", "CONSTANT_InvokeDynamic",
			"CONSTANT_Module", "CONSTANT_Package"};

	public static  String STATIC_INITIALIZER_NAME = "<clinit>";
	public static  String CONSTRUCTOR_NAME = "<init>";

	private static  String[] INTERFACES_IMPLEMENTED_BY_ARRAYS = {"java.lang.Cloneable", "java.io.Serializable"};

	public static  int MAX_CP_ENTRIES     = 65535;
	public static  int MAX_CODE_SIZE      = 65536; //bytes
	public static  int MAX_ARRAY_DIMENSIONS = 255;

//	public static  final short NOP              = 0;
//	public static  final short ACONST_NULL      = 1;
//	public static  final short ICONST_M1        = 2;
//	public static  final short ICONST_0         = 3;
//	public static  final short ICONST_1         = 4;
//	public static  final short ICONST_2         = 5;
//	public static  final short ICONST_3         = 6;
//	public static  final short ICONST_4         = 7;
//	public static  final short ICONST_5         = 8;
//	public static  final short LCONST_0         = 9;
//	public static  final short LCONST_1         = 10;
//	public static  final short FCONST_0         = 11;
//	public static  final short FCONST_1         = 12;
//	public static  final short FCONST_2         = 13;
//	public static  final short DCONST_0         = 14;
//	public static  final short DCONST_1         = 15;
//	public static  final short BIPUSH           = 16;
//	public static  final short SIPUSH           = 17;
//	public static  final short LDC              = 18;
//	public static  final short LDC_W            = 19;
//	public static  final short LDC2_W           = 20;
//	public static  final short ILOAD            = 21;
//	public static  final short LLOAD            = 22;
//	public static  final short FLOAD            = 23;
//	public static  final short DLOAD            = 24;
//	public static  final short ALOAD            = 25;
//	public static  final short ILOAD_0          = 26;
//	public static  final short ILOAD_1          = 27;
//	public static  final short ILOAD_2          = 28;
//	public static  final short ILOAD_3          = 29;
//	public static  final short LLOAD_0          = 30;
//	public static  final short LLOAD_1          = 31;
//	public static  final short LLOAD_2          = 32;
//	public static  final short LLOAD_3          = 33;
//	public static  final short FLOAD_0          = 34;
//	public static  final short FLOAD_1          = 35;
//	public static  final short FLOAD_2          = 36;
//	public static  final short FLOAD_3          = 37;
//	public static  final short DLOAD_0          = 38;
//	public static  final short DLOAD_1          = 39;
//	public static  final short DLOAD_2          = 40;
//	public static  final short DLOAD_3          = 41;
//	public static  final short ALOAD_0          = 42;
//	public static  final short ALOAD_1          = 43;
//	public static  final short ALOAD_2          = 44;
//	public static  final short ALOAD_3          = 45;
//	public static  final short IALOAD           = 46;
//	public static  final short LALOAD           = 47;
//	public static  final short FALOAD           = 48;
//	public static  final short DALOAD           = 49;
//	public static  final short AALOAD           = 50;
//	public static  final short BALOAD           = 51;
//	public static  final short CALOAD           = 52;
//	public static  final short SALOAD           = 53;
//	public static  final short ISTORE           = 54;
//	public static  final short LSTORE           = 55;
//	public static  final short FSTORE           = 56;
//	public static  final short DSTORE           = 57;
//	public static  final short ASTORE           = 58;
//	public static  final short ISTORE_0         = 59;
//	public static  final short ISTORE_1         = 60;
//	public static  final short ISTORE_2         = 61;
//	public static  final short ISTORE_3         = 62;
//	public static  final short LSTORE_0         = 63;
//	public static  final short LSTORE_1         = 64;
//	public static  final short LSTORE_2         = 65;
//	public static  final short LSTORE_3         = 66;
//	public static  final short FSTORE_0         = 67;
//	public static  final short FSTORE_1         = 68;
//	public static  final short FSTORE_2         = 69;
//	public static  final short FSTORE_3         = 70;
//	public static  final short DSTORE_0         = 71;
//	public static  final short DSTORE_1         = 72;
//	public static  final short DSTORE_2         = 73;
//	public static  final short DSTORE_3         = 74;
//	public static  final short ASTORE_0         = 75;
//	public static  final short ASTORE_1         = 76;
//	public static  final short ASTORE_2         = 77;
//	public static  final short ASTORE_3         = 78;
//	public static  final short IASTORE          = 79;
//	public static  final short LASTORE          = 80;
//	public static  final short FASTORE          = 81;
//	public static  final short DASTORE          = 82;
//	public static  final short AASTORE          = 83;
//	public static  final short BASTORE          = 84;
//	public static  final short CASTORE          = 85;
//	public static  final short SASTORE          = 86;
//	public static  final short POP              = 87;
//	public static  final short POP2             = 88;
//	public static  final short DUP              = 89;
//	public static  final short DUP_X1           = 90;
//	public static  final short DUP_X2           = 91;
//	public static  final short DUP2             = 92;
//	public static  final short DUP2_X1          = 93;
//	public static  final short DUP2_X2          = 94;
//	public static  final short SWAP             = 95;
//	public static  final short IADD             = 96;
//	public static  final short LADD             = 97;
//	public static  final short FADD             = 98;
//	public static  final short DADD             = 99;
//	public static  final short ISUB             = 100;
//	public static  final short LSUB             = 101;
//	public static  final short FSUB             = 102;
//	public static  final short DSUB             = 103;
//	public static  final short IMUL             = 104;
//	public static  final short LMUL             = 105;
//	public static  final short FMUL             = 106;
//	public static  final short DMUL             = 107;
//	public static  final short IDIV             = 108;
//	public static  final short LDIV             = 109;
//	public static  final short FDIV             = 110;
//	public static  final short DDIV             = 111;
//	public static  final short IREM             = 112;
//	public static  final short LREM             = 113;
//	public static  final short FREM             = 114;
//	public static  final short DREM             = 115;
//	public static  final short INEG             = 116;
//	public static  final short LNEG             = 117;
//	public static  final short FNEG             = 118;
//	public static  final short DNEG             = 119;
//	public static  final short ISHL             = 120;
//	public static  final short LSHL             = 121;
//	public static  final short ISHR             = 122;
//	public static  final short LSHR             = 123;
//	public static  final short IUSHR            = 124;
//	public static  final short LUSHR            = 125;
//	public static  final short IAND             = 126;
//	public static  final short LAND             = 127;
//	public static  final short IOR              = 128;
//	public static  final short LOR              = 129;
//	public static  final short IXOR             = 130;
//	public static  final short LXOR             = 131;
//	public static  final short IINC             = 132;
//	public static  final short I2L              = 133;
//	public static  final short I2F              = 134;
//	public static  final short I2D              = 135;
//	public static  final short L2I              = 136;
//	public static  final short L2F              = 137;
//	public static  final short L2D              = 138;
//	public static  final short F2I              = 139;
//	public static  final short F2L              = 140;
//	public static  final short F2D              = 141;
//	public static  final short D2I              = 142;
//	public static  final short D2L              = 143;
//	public static  final short D2F              = 144;
//	public static  final short I2B              = 145;
//	public static  final short INT2BYTE         = 145; // Old notation
//	public static  final short I2C              = 146;
//	public static  final short INT2CHAR         = 146; // Old notation
//	public static  final short I2S              = 147;
//	public static  final short INT2SHORT        = 147; // Old notation
//	public static  final short LCMP             = 148;
//	public static  final short FCMPL            = 149;
//	public static  final short FCMPG            = 150;
//	public static  final short DCMPL            = 151;
//	public static  final short DCMPG            = 152;
//	public static  final short IFEQ             = 153;
//	public static  final short IFNE             = 154;
//	public static  final short IFLT             = 155;
//	public static  final short IFGE             = 156;
//	public static  final short IFGT             = 157;
//	public static  final short IFLE             = 158;
//	public static  final short IF_ICMPEQ        = 159;
//	public static  final short IF_ICMPNE        = 160;
//	public static  final short IF_ICMPLT        = 161;
//	public static  final short IF_ICMPGE        = 162;
//	public static  final short IF_ICMPGT        = 163;
//	public static  final short IF_ICMPLE        = 164;
//	public static  final short IF_ACMPEQ        = 165;
//	public static  final short IF_ACMPNE        = 166;
//	public static  final short GOTO             = 167;
//	public static  final short JSR              = 168;
//	public static  final short RET              = 169;
//	public static  final short TABLESWITCH      = 170;
//	public static  final short LOOKUPSWITCH     = 171;
//	public static  final short IRETURN          = 172;
//
//
//	public static  final short LRETURN          = 173;
//
//
//	public static  final short FRETURN          = 174;
//
//
//	public static  final short DRETURN          = 175;
//
//
//	public static  final short ARETURN          = 176;
//
//
//	public static  final short RETURN           = 177;
//
//
//	public static  final short GETSTATIC        = 178;
//
//
//	public static  final short PUTSTATIC        = 179;
//
//
//	public static  final short GETFIELD         = 180;
//
//
//	public static  final short PUTFIELD         = 181;
//
//
//	public static  final short INVOKEVIRTUAL    = 182;
//
//
//	public static  final short INVOKESPECIAL    = 183;
//
//
//	public static  final short INVOKENONVIRTUAL = 183; // Old name in JDK 1.0
//
//
//	public static  final short INVOKESTATIC     = 184;
//
//
//	public static  final short INVOKEINTERFACE  = 185;
//
//
//	public static  final short INVOKEDYNAMIC    = 186;
//
//
//	public static  final short NEW              = 187;
//
//
//	public static  final short NEWARRAY         = 188;
//
//
//	public static  final short ANEWARRAY        = 189;
//
//
//	public static  final short ARRAYLENGTH      = 190;
//
//
//	public static  final short ATHROW           = 191;
//
//
//	public static  final short CHECKCAST        = 192;
//
//
//	public static  final short INSTANCEOF       = 193;
//
//
//	public static  final short MONITORENTER     = 194;
//
//
//	public static  final short MONITOREXIT      = 195;
//
//
//	public static  final short WIDE             = 196;
//
//
//	public static  final short MULTIANEWARRAY   = 197;
//
//
//	public static  final short IFNULL           = 198;
//
//
//	public static  final short IFNONNULL        = 199;
//
//
//	public static  final short GOTO_W           = 200;
//
//
//	public static  final short JSR_W            = 201;
//
//
//	public static  final short BREAKPOINT                = 202;
//
//
//	public static  final short LDC_QUICK                 = 203;
//
//
//	public static  final short LDC_W_QUICK               = 204;
//
//
//	public static  final short LDC2_W_QUICK              = 205;
//
//
//	public static  final short GETFIELD_QUICK            = 206;
//
//
//	public static  final short PUTFIELD_QUICK            = 207;
//
//
//	public static  final short GETFIELD2_QUICK           = 208;
//
//
//	public static  final short PUTFIELD2_QUICK           = 209;
//
//
//	public static  final short GETSTATIC_QUICK           = 210;
//
//
//	public static  final short PUTSTATIC_QUICK           = 211;
//
//
//	public static  final short GETSTATIC2_QUICK          = 212;
//
//
//	public static  final short PUTSTATIC2_QUICK          = 213;
//
//
//	public static  final short INVOKEVIRTUAL_QUICK       = 214;
//
//
//	public static  final short INVOKENONVIRTUAL_QUICK    = 215;
//
//
//	public static  final short INVOKESUPER_QUICK         = 216;
//
//
//	public static  final short INVOKESTATIC_QUICK        = 217;
//
//
//	public static  final short INVOKEINTERFACE_QUICK     = 218;
//
//
//	public static  final short INVOKEVIRTUALOBJECT_QUICK = 219;
//
//
//	public static  final short NEW_QUICK                 = 221;
//
//
//	public static  final short ANEWARRAY_QUICK           = 222;
//
//
//	public static  final short MULTIANEWARRAY_QUICK      = 223;
//
//
//	public static  final short CHECKCAST_QUICK           = 224;
//
//
//	public static  final short INSTANCEOF_QUICK          = 225;
//
//
//	public static  final short INVOKEVIRTUAL_QUICK_W     = 226;
//
//
//	public static  final short GETFIELD_QUICK_W          = 227;
//
//
//	public static  final short PUTFIELD_QUICK_W          = 228;
//
//
//	public static  final short IMPDEP1                   = 254;
//
//
//	public static  final short IMPDEP2                   = 255;

	public static  final short PUSH             = 4711;
	public static  final short SWITCH           = 4712;
	public static  final short  UNDEFINED      = -1;
	public static  final short  UNPREDICTABLE  = -2;
	public static  final short  RESERVED       = -3;
	public static  String ILLEGAL_OPCODE = "<illegal opcode>";
	public static  String ILLEGAL_TYPE   = "<illegal type>";

	public static  final byte T_BOOLEAN = 4;

	/** Char data type.
	 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P">
	 * Static Constraints in the Java Virtual Machine Specification</a> */
	public static  final byte T_CHAR    = 5;

	/** Float data type.
	 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P">
	 * Static Constraints in the Java Virtual Machine Specification</a> */
	public static  final byte T_FLOAT   = 6;

	/** Double data type.
	 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P">
	 * Static Constraints in the Java Virtual Machine Specification</a> */
	public static  final byte T_DOUBLE  = 7;

	/** Byte data type.
	 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P">
	 * Static Constraints in the Java Virtual Machine Specification</a> */
	public static  final byte T_BYTE    = 8;

	/** Short data type.
	 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P">
	 * Static Constraints in the Java Virtual Machine Specification</a> */
	public static  final byte T_SHORT   = 9;

	/** Int data type.
	 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P">
	 * Static Constraints in the Java Virtual Machine Specification</a> */
	public static  final byte T_INT     = 10;

	/** Long data type.
	 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P">
	 * Static Constraints in the Java Virtual Machine Specification</a> */
	public static  final byte T_LONG    = 11;

	/** Void data type (non-standard). */
	public static  final byte T_VOID      = 12; // Non-standard

	/** Array data type. */
	public static  final byte T_ARRAY     = 13;

	/** Object data type. */
	public static  final byte T_OBJECT    = 14;

	/** Reference data type (deprecated). */
	public static  final byte T_REFERENCE = 14; // Deprecated

	/** Unknown data type. */
	public static  final byte T_UNKNOWN   = 15;

	/** Address data type. */
	public static  final byte T_ADDRESS   = 16;

	/** The primitive type names corresponding to the T_XX constants,
	 * e.g., TYPE_NAMES[T_INT] = "int"
	 */
	private static  String[] TYPE_NAMES = {
			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
			"boolean", "char", "float", "double", "byte", "short", "int", "long",
			"void", "array", "object", "unknown", "address"
	};

	/** The primitive class names corresponding to the T_XX constants,
	 * e.g., CLASS_TYPE_NAMES[T_INT] = "java.lang.Integer"
	 */
	private static  String[] CLASS_TYPE_NAMES = {
			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
			"java.lang.Boolean", "java.lang.Character", "java.lang.Float",
			"java.lang.Double", "java.lang.Byte", "java.lang.Short",
			"java.lang.Integer", "java.lang.Long", "java.lang.Void",
			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE,  ILLEGAL_TYPE
	};

	/** The signature characters corresponding to primitive types,
	 * e.g., SHORT_TYPE_NAMES[T_INT] = "I"
	 */
	private static  String[] SHORT_TYPE_NAMES = {
			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
			"Z", "C", "F", "D", "B", "S", "I", "J",
			"V", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE
	};

	/**
	 * Number of byte code operands for each opcode, i.e., number of bytes after the tag byte
	 * itself.  Indexed by opcode, so NO_OF_OPERANDS[BIPUSH] = the number of operands for a bipush
	 * instruction.
	 */
	private static  short[] NO_OF_OPERANDS = {
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
	private static  short[][] TYPE_OF_OPERANDS = {
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
	private static  String[] OPCODE_NAMES = {
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
	public static  int OPCODE_NAMES_LENGTH = OPCODE_NAMES.length;

	/**
	 * Number of words consumed on operand stack by instructions.
	 * Indexed by opcode.  CONSUME_STACK[FALOAD] = number of words
	 * consumed from the stack by a faload instruction.
	 */
	private static  int[] CONSUME_STACK = {
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
	private static  int[] PRODUCE_STACK = {
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


//	public static  byte ATTR_UNKNOWN                                 = -1;
//	public static  byte ATTR_SOURCE_FILE                             = 0;
//	public static  byte ATTR_CONSTANT_VALUE                          = 1;
//	public static  byte ATTR_CODE                                    = 2;
//	public static  byte ATTR_EXCEPTIONS                              = 3;
//	public static  byte ATTR_LINE_NUMBER_TABLE                       = 4;
//	public static  byte ATTR_LOCAL_VARIABLE_TABLE                    = 5;
//	public static  byte ATTR_INNER_CLASSES                           = 6;
//	public static  byte ATTR_SYNTHETIC                               = 7;
//	public static  byte ATTR_DEPRECATED                              = 8;
//	public static  byte ATTR_PMG                                     = 9;
//	public static  byte ATTR_SIGNATURE                               = 10;
//	public static  byte ATTR_STACK_MAP                               = 11;
//	public static  byte ATTR_RUNTIME_VISIBLE_ANNOTATIONS             = 12;
//	public static  byte ATTR_RUNTIME_INVISIBLE_ANNOTATIONS           = 13;
//	public static  byte ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS   = 14;
//	public static  byte ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = 15;
//	public static  byte ATTR_ANNOTATION_DEFAULT                      = 16;
//	public static  byte ATTR_LOCAL_VARIABLE_TYPE_TABLE               = 17;
//	public static  byte ATTR_ENCLOSING_METHOD                        = 18;
//	public static  byte ATTR_STACK_MAP_TABLE                         = 19;
//	public static  byte ATTR_BOOTSTRAP_METHODS                       = 20;
//	public static  byte ATTR_METHOD_PARAMETERS                       = 21;
//	public static  byte ATTR_MODULE                                  = 22;
//	public static  byte ATTR_MODULE_PACKAGES                         = 23;
//	public static  byte ATTR_MODULE_MAIN_CLASS                       = 24;
//	public static  byte ATTR_NEST_HOST                               = 25;
//	public static  byte ATTR_NEST_MEMBERS                            = 26;
	public static  final short KNOWN_ATTRIBUTES = 27; // count of attributes
	private static  String[] ATTRIBUTE_NAMES = {
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
	/** Constants used in the StackMap attribute.
	 */
	public static  byte ITEM_Bogus      = 0;
	public static  byte ITEM_Integer    = 1;
	public static  byte ITEM_Float      = 2;
	public static  byte ITEM_Double     = 3;
	public static  byte ITEM_Long       = 4;
	public static  byte ITEM_Null       = 5;
	public static  byte ITEM_InitObject = 6;
	public static  byte ITEM_Object     = 7;
	public static  byte ITEM_NewObject  = 8;
	private static  String[] ITEM_NAMES = {
			"Bogus", "Integer", "Float", "Double", "Long",
			"Null", "InitObject", "Object", "NewObject"
	};

	/** Constants used to identify StackMapEntry types.
	 *
	 * For those types which can specify a range, the
	 * constant names the lowest value.
	 */
	public static  int SAME_FRAME = 0;

	public static  int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;

	public static  int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;

	public static  int CHOP_FRAME = 248;
	public static  int SAME_FRAME_EXTENDED = 251;
	public static  int APPEND_FRAME = 252;
	public static  int FULL_FRAME = 255;
	/** Constants that define the maximum value of
	 * those constants which store ranges. */

	public static  int SAME_FRAME_MAX = 63;
	public static  int SAME_LOCALS_1_STACK_ITEM_FRAME_MAX = 127;
	public static  int CHOP_FRAME_MAX = 250;
	public static  int APPEND_FRAME_MAX = 254;
	public static  byte REF_getField         = 1;

	public static  byte REF_getStatic        = 2;

	public static  byte REF_putField         = 3;

	public static  byte REF_putStatic        = 4;
	public static  byte REF_invokeVirtual    = 5;
	public static  byte REF_invokeStatic     = 6;
	public static  byte REF_invokeSpecial    = 7;
	public static  byte REF_newInvokeSpecial = 8;
	public static  byte REF_invokeInterface  = 9;
	/**
	 * The names of the reference_kinds of a CONSTANT_MethodHandle_info.
	 */
	private static  String[] METHODHANDLE_NAMES = {
			"", "getField", "getStatic", "putField", "putStatic", "invokeVirtual",
			"invokeStatic", "invokeSpecial", "newInvokeSpecial", "invokeInterface" };

	private Const() { } // not instantiable
	//#formatter:on
	/**
	 * @param index
	 * @return the ACCESS_NAMES entry at the given index
	 * @since 6.0
	 */
	public static String getAccessName( int index) {
		return ACCESS_NAMES[index];
	}
	/**
	 *
	 * @param index
	 * @return the attribute name
	 * @since 6.0
	 */
	public static String getAttributeName( int index) {
		return ATTRIBUTE_NAMES[index];
	}
	/**
	 * The primitive class names corresponding to the T_XX constants,
	 * e.g., CLASS_TYPE_NAMES[T_INT] = "java.lang.Integer"
	 * @param index
	 * @return the class name
	 * @since 6.0
	 */
	public static String getClassTypeName( int index) {
		return CLASS_TYPE_NAMES[index];
	}


	// Constants defining the behavior of the Method Handles (JVMS �5.4.3.5)

	/**
	 *
	 * @param index
	 * @return the CONSTANT_NAMES entry at the given index
	 * @since 6.0
	 */
	public static String getConstantName( int index) {
		return CONSTANT_NAMES[index];
	}
	/**
	 *
	 * @param index
	 * @return Number of words consumed on operand stack
	 * @since 6.0
	 */
	public static int getConsumeStack( int index) {
		return CONSUME_STACK[index];
	}
	/**
	 * @since 6.0
	 */
	public static Iterable<String> getInterfacesImplementedByArrays() {
		return Collections.unmodifiableList(Arrays.asList(INTERFACES_IMPLEMENTED_BY_ARRAYS));
	}
	/**
	 *
	 * @param index
	 * @return the item name
	 * @since 6.0
	 */
	public static String getItemName( int index) {
		return ITEM_NAMES[index];
	}
	/**
	 *
	 * @param index
	 * @return the method handle name
	 * @since 6.0
	 */
	public static String getMethodHandleName( int index) {
		return METHODHANDLE_NAMES[index];
	}
	/**
	 *
	 * @param index
	 * @return Number of byte code operands
	 * @since 6.0
	 */
	public static short getNoOfOperands( int index) {
		return NO_OF_OPERANDS[index];
	}
	public static String getOpcodeName(InstructionOpCodes index) {
	    return OPCODE_NAMES[index.getOpcode()];
	}
	/**
	 * @since 6.0
	 */
	public static String getOpcodeName( int index) {
		return OPCODE_NAMES[index];
	}
	/**
	 * @since 6.0
	 */
	public static short getOperandType( int opcode,  int index) {
		return TYPE_OF_OPERANDS[opcode][index];
	}
	/**
	 * @since 6.0
	 */
	public static long getOperandTypeCount( int opcode) {
		return TYPE_OF_OPERANDS[opcode].length;
	}

	/**
	 *
	 * @param index
	 * @return Number of words produced onto operand stack
	 * @since 6.0
	 */
	public static int getProduceStack( int index) {
		return PRODUCE_STACK[index];
	}

	/**
	 *
	 * @param index
	 * @return the short type name
	 * @since 6.0
	 */
	public static String getShortTypeName( int index) {
		return SHORT_TYPE_NAMES[index];
	}

	/**
	 * The primitive type names corresponding to the T_XX constants,
	 * e.g., TYPE_NAMES[T_INT] = "int"
	 * @param index
	 * @return the type name
	 * @since 6.0
	 */
	public static String getTypeName( int index) {
		return TYPE_NAMES[index];
	}
}
