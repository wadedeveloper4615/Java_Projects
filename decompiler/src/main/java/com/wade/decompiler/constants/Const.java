package com.wade.decompiler.constants;

import java.util.Arrays;
import java.util.Collections;

import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.Version;

public class Const {
    //@formatter:off
	public static  int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;

	public static  final short MAJOR = (short) Version.Version_1_1.getMajor();
	public static  final short MINOR = (short) Version.Version_1_1.getMinor();

	public static  int MAX_SHORT = 65535; // 2^16 - 1
	public static  int MAX_BYTE  = 255; // 2^8 - 1


	public static  final short MAX_ACC_FLAG     = (short) ClassAccessFlags.ACC_ENUM.getFlag();
	public static  int MAX_ACC_FLAG_I     = 0x8000; // ACC_MODULE is negative as a short

	public static  String STATIC_INITIALIZER_NAME = "<clinit>";
	public static  String CONSTRUCTOR_NAME = "<init>";

	private static  String[] INTERFACES_IMPLEMENTED_BY_ARRAYS = {"java.lang.Cloneable", "java.io.Serializable"};

	public static  int MAX_CP_ENTRIES     = 65535;
	public static  int MAX_CODE_SIZE      = 65536; //bytes
	public static  int MAX_ARRAY_DIMENSIONS = 255;

	public static  final short PUSH             = 4711;
	public static  final short SWITCH           = 4712;
	public static  final short  UNDEFINED      = -1;
	public static  final short  UNPREDICTABLE  = -2;
	public static  final short  RESERVED       = -3;
	public static  String ILLEGAL_OPCODE = "<illegal opcode>";
	public static  String ILLEGAL_TYPE   = "<illegal type>";

//	public static  final byte T_BOOLEAN = 4;
//	public static  final byte T_CHAR    = 5;
//	public static  final byte T_FLOAT   = 6;
//	public static  final byte T_DOUBLE  = 7;
//	public static  final byte T_BYTE    = 8;
//	public static  final byte T_SHORT   = 9;
//	public static  final byte T_INT     = 10;
//	public static  final byte T_LONG    = 11;
//	public static  final byte T_VOID      = 12; // Non-standard
//	public static  final byte T_ARRAY     = 13;
//	public static  final byte T_OBJECT    = 14;
//	public static  final byte T_REFERENCE = 14; // Deprecated
//	public static  final byte T_UNKNOWN   = 15;
//	public static  final byte T_ADDRESS   = 16;
//	private static  String[] TYPE_NAMES = {
//			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
//			"boolean", "char", "float", "double", "byte", "short", "int", "long",
//			"void", "array", "object", "unknown", "address"
//	};
//
//	private static  String[] CLASS_TYPE_NAMES = {
//			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
//			"java.lang.Boolean", "java.lang.Character", "java.lang.Float",
//			"java.lang.Double", "java.lang.Byte", "java.lang.Short",
//			"java.lang.Integer", "java.lang.Long", "java.lang.Void",
//			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE,  ILLEGAL_TYPE
//	};
//
//	private static  String[] SHORT_TYPE_NAMES = {
//			ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
//			"Z", "C", "F", "D", "B", "S", "I", "J",
//			"V", ILLEGAL_TYPE, ILLEGAL_TYPE, ILLEGAL_TYPE
//	};

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

	public static  int SAME_FRAME = 0;
	public static  int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
	public static  int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;

	public static  int CHOP_FRAME = 248;
	public static  int SAME_FRAME_EXTENDED = 251;
	public static  int APPEND_FRAME = 252;
	public static  int FULL_FRAME = 255;
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

//	public static String getClassTypeName( int index) {
//		return CLASS_TYPE_NAMES[index];
//	}

	public static Iterable<String> getInterfacesImplementedByArrays() {
		return Collections.unmodifiableList(Arrays.asList(INTERFACES_IMPLEMENTED_BY_ARRAYS));
	}

	public static String getItemName( int index) {
		return ITEM_NAMES[index];
	}

	public static String getMethodHandleName( int index) {
		return METHODHANDLE_NAMES[index];
	}

//	public static String getShortTypeName( int index) {
//		return SHORT_TYPE_NAMES[index];
//	}
//
//	public static String getTypeName( int index) {
//		return TYPE_NAMES[index];
//	}
}
