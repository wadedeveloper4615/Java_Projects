package com.wade.decompiler.constants;

import java.util.Arrays;
import java.util.Collections;

import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.enums.Version;

//@formatter:off
//@formatter:off
public final class Const {
    public static final int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;
    @Deprecated
    public static final short MAJOR_1_9 = (short) Version.Version_9.getMajor();
    @Deprecated
    public static final short MINOR_1_9 = (short) Version.Version_9.getMinor();
    public static final short MAJOR =  (short) Version.Version_1_1.getMajor();
    public static final short MINOR = (short) Version.Version_1_1.getMajor();
    public static final int MAX_SHORT = 65535; // 2^16 - 1
    public static final int MAX_BYTE  = 255; // 2^8 - 1

    public static final short MAX_ACC_FLAG     = (short) ClassAccessFlags.ACC_ENUM.getIndex();
    public static final int MAX_ACC_FLAG_I = 0x8000; // ACC_MODULE is negative as a short

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

    public static final int OPCODE_NAMES_LENGTH = InstructionOpCodes.IMPDEP2.getOpcode();

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
        "", "getField", "getStatic", "putField", "putStatic", "invokeVirtual",
        "invokeStatic", "invokeSpecial", "newInvokeSpecial", "invokeInterface" };

    private Const() { } // not instantiable

    public static Iterable<String> getInterfacesImplementedByArrays() {
        return Collections.unmodifiableList(Arrays.asList(INTERFACES_IMPLEMENTED_BY_ARRAYS));
    }

    public static String getMethodHandleName(final int index) {
        return METHODHANDLE_NAMES[index];
    }
  }
