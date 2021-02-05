package com.wade.app.enums;

import java.io.IOException;

import com.wade.app.Const;

public enum ClassFileInstructions {
    NOP((short) 0, "NOP", (short) 0, new short[] {}, 0, 0),

    ACONST_NULL((short) 1, "ACONST_NULL", (short) 0, new short[] {}, 0, 1),

    ICONST_M1((short) 2, "ICONST_M1", (short) 0, new short[] {}, 0, 1),

    ICONST_0((short) 3, "ICONST_0", (short) 0, new short[] {}, 0, 1),

    ICONST_1((short) 4, "ICONST_1", (short) 0, new short[] {}, 0, 1),

    ICONST_2((short) 5, "ICONST_2", (short) 0, new short[] {}, 0, 1),

    ICONST_3((short) 6, "ICONST_3", (short) 0, new short[] {}, 0, 1),

    ICONST_4((short) 7, "ICONST_4", (short) 0, new short[] {}, 0, 1),

    ICONST_5((short) 8, "ICONST_5", (short) 0, new short[] {}, 0, 1),

    LCONST_0((short) 9, "LCONST_0", (short) 0, new short[] {}, 0, 2),

    LCONST_1((short) 10, "LCONST_1", (short) 0, new short[] {}, 0, 2),

    FCONST_0((short) 11, "FCONST_0", (short) 0, new short[] {}, 0, 1),

    FCONST_1((short) 12, "FCONST_1", (short) 0, new short[] {}, 0, 1),

    FCONST_2((short) 13, "FCONST_2", (short) 0, new short[] {}, 0, 1),

    DCONST_0((short) 14, "DCONST_0", (short) 0, new short[] {}, 0, 2),

    DCONST_1((short) 15, "DCONST_1", (short) 0, new short[] { Const.T_BYTE }, 0, 2),

    BIPUSH((short) 16, "BIPUSH", (short) 1, new short[] { Const.T_SHORT }, 0, 1),

    SIPUSH((short) 17, "SIPUSH", (short) 2, new short[] { Const.T_BYTE }, 0, 1),

    LDC((short) 18, "LDC", (short) 1, new short[] { Const.T_SHORT }, 0, 1),

    LDC_W((short) 19, "LDC_W", (short) 2, new short[] { Const.T_SHORT }, 0, 1),

    LDC2_W((short) 20, "LDC2_W", (short) 2, new short[] { Const.T_SHORT }, 0, 2),

    ILOAD((short) 21, "ILOAD", (short) 1, new short[] { Const.T_BYTE }, 0, 1),

    LLOAD((short) 22, "LLOAD", (short) 1, new short[] { Const.T_BYTE }, 0, 2),

    FLOAD((short) 23, "FLOAD", (short) 1, new short[] { Const.T_BYTE }, 0, 1),

    DLOAD((short) 24, "DLOAD", (short) 1, new short[] { Const.T_BYTE }, 0, 2),

    ALOAD((short) 25, "ALOAD", (short) 1, new short[] { Const.T_BYTE }, 0, 1),

    ILOAD_0((short) 26, "ILOAD_0", (short) 0, new short[] {}, 0, 1),

    ILOAD_1((short) 27, "ILOAD_1", (short) 0, new short[] {}, 0, 1),

    ILOAD_2((short) 28, "ILOAD_2", (short) 0, new short[] {}, 0, 1),

    ILOAD_3((short) 29, "ILOAD_3", (short) 0, new short[] {}, 0, 1),

    LLOAD_0((short) 30, "LLOAD_0", (short) 0, new short[] {}, 0, 2),

    LLOAD_1((short) 31, "LLOAD_1", (short) 0, new short[] {}, 0, 2),

    LLOAD_2((short) 32, "LLOAD_2", (short) 0, new short[] {}, 0, 2),

    LLOAD_3((short) 33, "LLOAD_3", (short) 0, new short[] {}, 0, 2),

    FLOAD_0((short) 34, "FLOAD_0", (short) 0, new short[] {}, 0, 1),

    FLOAD_1((short) 35, "FLOAD_1", (short) 0, new short[] {}, 0, 1),

    FLOAD_2((short) 36, "FLOAD_2", (short) 0, new short[] {}, 0, 1),

    FLOAD_3((short) 37, "FLOAD_3", (short) 0, new short[] {}, 0, 1),

    DLOAD_0((short) 38, "DLOAD_0", (short) 0, new short[] {}, 0, 2),

    DLOAD_1((short) 39, "DLOAD_1", (short) 0, new short[] {}, 0, 2),

    DLOAD_2((short) 40, "DLOAD_2", (short) 0, new short[] {}, 0, 2),

    DLOAD_3((short) 41, "DLOAD_3", (short) 0, new short[] {}, 0, 2),

    ALOAD_0((short) 42, "ALOAD_0", (short) 0, new short[] {}, 0, 1),

    ALOAD_1((short) 43, "ALOAD_1", (short) 0, new short[] {}, 0, 1),

    ALOAD_2((short) 44, "ALOAD_2", (short) 0, new short[] {}, 0, 1),

    ALOAD_3((short) 45, "ALOAD_3", (short) 0, new short[] {}, 0, 1),

    IALOAD((short) 46, "IALOAD", (short) 0, new short[] {}, 2, 1),

    LALOAD((short) 47, "LALOAD", (short) 0, new short[] {}, 2, 2),

    FALOAD((short) 48, "FALOAD", (short) 0, new short[] {}, 2, 1),

    DALOAD((short) 49, "DALOAD", (short) 0, new short[] {}, 2, 2),

    AALOAD((short) 50, "AALOAD", (short) 0, new short[] {}, 2, 1),

    BALOAD((short) 51, "BALOAD", (short) 0, new short[] {}, 2, 1),

    CALOAD((short) 52, "CALOAD", (short) 0, new short[] {}, 2, 1),

    SALOAD((short) 53, "SALOAD", (short) 0, new short[] {}, 2, 1),

    ISTORE((short) 54, "ISTORE", (short) 1, new short[] { Const.T_BYTE }, 1, 0),

    LSTORE((short) 55, "LSTORE", (short) 1, new short[] { Const.T_BYTE }, 2, 0),

    FSTORE((short) 56, "FSTORE", (short) 1, new short[] { Const.T_BYTE }, 1, 0),

    DSTORE((short) 57, "DSTORE", (short) 1, new short[] { Const.T_BYTE }, 2, 0),

    ASTORE((short) 58, "ASTORE", (short) 1, new short[] { Const.T_BYTE }, 0, 0),

    ISTORE_0((short) 59, "ISTORE_0", (short) 0, new short[] {}, 1, 0),

    ISTORE_1((short) 60, "ISTORE_1", (short) 0, new short[] {}, 1, 0),

    ISTORE_2((short) 61, "ISTORE_2", (short) 0, new short[] {}, 1, 0),

    ISTORE_3((short) 62, "ISTORE_3", (short) 0, new short[] {}, 1, 0),

    LSTORE_0((short) 63, "LSTORE_0", (short) 0, new short[] {}, 2, 0),

    LSTORE_1((short) 64, "LSTORE_1", (short) 0, new short[] {}, 2, 0),

    LSTORE_2((short) 65, "LSTORE_2", (short) 0, new short[] {}, 2, 0),

    LSTORE_3((short) 66, "LSTORE_3", (short) 0, new short[] {}, 2, 0),

    FSTORE_0((short) 67, "FSTORE_0", (short) 0, new short[] {}, 1, 0),

    FSTORE_1((short) 68, "FSTORE_1", (short) 0, new short[] {}, 1, 0),

    FSTORE_2((short) 69, "FSTORE_2", (short) 0, new short[] {}, 1, 0),

    FSTORE_3((short) 70, "FSTORE_3", (short) 0, new short[] {}, 1, 0),

    DSTORE_0((short) 71, "DSTORE_0", (short) 0, new short[] {}, 2, 0),

    DSTORE_1((short) 72, "DSTORE_1", (short) 0, new short[] {}, 2, 0),

    DSTORE_2((short) 73, "DSTORE_2", (short) 0, new short[] {}, 2, 0),

    DSTORE_3((short) 74, "DSTORE_3", (short) 0, new short[] {}, 2, 0),

    ASTORE_0((short) 75, "ASTORE_0", (short) 0, new short[] {}, 1, 0),

    ASTORE_1((short) 76, "ASTORE_1", (short) 0, new short[] {}, 1, 0),

    ASTORE_2((short) 77, "ASTORE_2", (short) 0, new short[] {}, 1, 0),

    ASTORE_3((short) 78, "ASTORE_3", (short) 0, new short[] {}, 1, 0),

    IASTORE((short) 79, "IASTORE", (short) 0, new short[] {}, 3, 0),

    LASTORE((short) 80, "LASTORE", (short) 0, new short[] {}, 4, 0),

    FASTORE((short) 81, "FASTORE", (short) 0, new short[] {}, 3, 0),

    DASTORE((short) 82, "DASTORE", (short) 0, new short[] {}, 4, 0),

    AASTORE((short) 83, "AASTORE", (short) 0, new short[] {}, 3, 0),

    BASTORE((short) 84, "BASTORE", (short) 0, new short[] {}, 3, 0),

    CASTORE((short) 85, "CASTORE", (short) 0, new short[] {}, 3, 0),

    SASTORE((short) 86, "SASTORE", (short) 0, new short[] {}, 3, 0),

    POP((short) 87, "POP", (short) 0, new short[] {}, 1, 0),

    POP2((short) 88, "POP2", (short) 0, new short[] {}, 2, 0),

    DUP((short) 89, "DUP", (short) 0, new short[] {}, 1, 2),

    DUP_X1((short) 90, "DUP_X1", (short) 0, new short[] {}, 2, 3),

    DUP_X2((short) 91, "DUP_X2", (short) 0, new short[] {}, 3, 4),

    DUP2((short) 92, "DUP2", (short) 0, new short[] {}, 2, 4),

    DUP2_X1((short) 93, "DUP2_X1", (short) 0, new short[] {}, 3, 5),

    DUP2_X2((short) 94, "DUP2_X2", (short) 0, new short[] {}, 4, 6),

    SWAP((short) 95, "SWAP", (short) 0, new short[] {}, 2, 2),

    IADD((short) 96, "IADD", (short) 0, new short[] {}, 2, 1),

    LADD((short) 97, "LADD", (short) 0, new short[] {}, 4, 2),

    FADD((short) 98, "FADD", (short) 0, new short[] {}, 2, 1),

    DADD((short) 99, "DADD", (short) 0, new short[] {}, 4, 2),

    ISUB((short) 100, "ISUB", (short) 0, new short[] {}, 2, 1),

    LSUB((short) 101, "LSUB", (short) 0, new short[] {}, 4, 2),

    FSUB((short) 102, "FSUB", (short) 0, new short[] {}, 2, 1),

    DSUB((short) 103, "DSUB", (short) 0, new short[] {}, 4, 2),

    IMUL((short) 104, "IMUL", (short) 0, new short[] {}, 2, 1),

    LMUL((short) 105, "LMUL", (short) 0, new short[] {}, 4, 2),

    FMUL((short) 106, "FMUL", (short) 0, new short[] {}, 2, 1),

    DMUL((short) 107, "DMUL", (short) 0, new short[] {}, 4, 2),

    IDIV((short) 108, "IDIV", (short) 0, new short[] {}, 2, 1),

    LDIV((short) 109, "LDIV", (short) 0, new short[] {}, 4, 2),

    FDIV((short) 110, "FDIV", (short) 0, new short[] {}, 2, 1),

    DDIV((short) 111, "DDIV", (short) 0, new short[] {}, 4, 2),

    IREM((short) 112, "IREM", (short) 0, new short[] {}, 2, 1),

    LREM((short) 113, "LREM", (short) 0, new short[] {}, 4, 2),

    FREM((short) 114, "FREM", (short) 0, new short[] {}, 2, 1),

    DREM((short) 115, "DREM", (short) 0, new short[] {}, 4, 2),

    INEG((short) 116, "INEG", (short) 0, new short[] {}, 1, 1),

    LNEG((short) 117, "LNEG", (short) 0, new short[] {}, 2, 2),

    FNEG((short) 118, "FNEG", (short) 0, new short[] {}, 1, 1),

    DNEG((short) 119, "DNEG", (short) 0, new short[] {}, 2, 2),

    ISHL((short) 120, "ISHL", (short) 0, new short[] {}, 2, 1),

    LSHL((short) 121, "LSHL", (short) 0, new short[] {}, 3, 2),

    ISHR((short) 122, "ISHR", (short) 0, new short[] {}, 2, 1),

    LSHR((short) 123, "LSHR", (short) 0, new short[] {}, 3, 2),

    IUSHR((short) 124, "IUSHR", (short) 0, new short[] {}, 2, 1),

    LUSHR((short) 125, "LUSHR", (short) 0, new short[] {}, 3, 2),

    IAND((short) 126, "IAND", (short) 0, new short[] {}, 2, 1),

    LAND((short) 127, "LAND", (short) 0, new short[] {}, 4, 2),

    IOR((short) 128, "IOR", (short) 0, new short[] {}, 2, 1),

    LOR((short) 129, "LOR", (short) 0, new short[] {}, 4, 2),

    IXOR((short) 130, "IXOR", (short) 0, new short[] {}, 2, 1),

    LXOR((short) 131, "LXOR", (short) 0, new short[] { Const.T_BYTE, Const.T_BYTE }, 2, 2),

    IINC((short) 132, "IINC", (short) 2, new short[] {}, 0, 0),

    I2L((short) 133, "I2L", (short) 0, new short[] {}, 1, 2),

    I2F((short) 134, "I2F", (short) 0, new short[] {}, 1, 1),

    I2D((short) 135, "I2D", (short) 0, new short[] {}, 1, 2),

    L2I((short) 136, "L2I", (short) 0, new short[] {}, 2, 1),

    L2F((short) 137, "L2F", (short) 0, new short[] {}, 2, 1),

    L2D((short) 138, "L2D", (short) 0, new short[] {}, 2, 2),

    F2I((short) 139, "F2I", (short) 0, new short[] {}, 1, 1),

    F2L((short) 140, "F2L", (short) 0, new short[] {}, 1, 2),

    F2D((short) 141, "F2D", (short) 0, new short[] {}, 1, 2),

    D2I((short) 142, "D2I", (short) 0, new short[] {}, 2, 1),

    D2L((short) 143, "D2L", (short) 0, new short[] {}, 2, 2),

    D2F((short) 144, "D2F", (short) 0, new short[] {}, 2, 1),

    I2B((short) 145, "I2B", (short) 0, new short[] {}, 1, 2),

    INT2BYTE((short) 145, "INT2BYTE", (short) 0, new short[] {}, 1, 2),

    // Old notation
    I2C((short) 146, "I2C", (short) 0, new short[] {}, 1, 1),

    INT2CHAR((short) 146, "INT2CHAR", (short) 0, new short[] {}, 1, 1),

    // Old notation
    I2S((short) 147, "I2S", (short) 0, new short[] {}, 1, 1),

    INT2SHORT((short) 147, "INT2SHORT", (short) 0, new short[] {}, 1, 1),

    // Old notation
    LCMP((short) 148, "LCMP", (short) 0, new short[] {}, 4, 1),

    FCMPL((short) 149, "FCMPL", (short) 0, new short[] {}, 2, 1),

    FCMPG((short) 150, "FCMPG", (short) 0, new short[] {}, 2, 1),

    DCMPL((short) 151, "DCMPL", (short) 0, new short[] {}, 4, 1),

    DCMPG((short) 152, "DCMPG", (short) 0, new short[] { Const.T_SHORT }, 4, 1),

    IFEQ((short) 153, "IFEQ", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    IFNE((short) 154, "IFNE", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    IFLT((short) 155, "IFLT", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    IFGE((short) 156, "IFGE", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    IFGT((short) 157, "IFGT", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    IFLE((short) 158, "IFLE", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    IF_ICMPEQ((short) 159, "IF_ICMPEQ", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    IF_ICMPNE((short) 160, "IF_ICMPNE", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    IF_ICMPLT((short) 161, "IF_ICMPLT", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    IF_ICMPGE((short) 162, "IF_ICMPGE", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    IF_ICMPGT((short) 163, "IF_ICMPGT", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    IF_ICMPLE((short) 164, "IF_ICMPLE", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    IF_ACMPEQ((short) 165, "IF_ACMPEQ", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    IF_ACMPNE((short) 166, "IF_ACMPNE", (short) 2, new short[] { Const.T_SHORT }, 2, 0),

    GOTO((short) 167, "GOTO", (short) 2, new short[] { Const.T_SHORT }, 0, 0),

    JSR((short) 168, "JSR", (short) 2, new short[] { Const.T_SHORT }, 0, 1),

    RET((short) 169, "RET", (short) 2, new short[] { Const.T_BYTE }, 0, 0),

    TABLESWITCH((short) 170, "TABLESWITCH", Const.UNPREDICTABLE, new short[] {}, 1, 0),

    LOOKUPSWITCH((short) 171, "LOOKUPSWITCH", Const.UNPREDICTABLE, new short[] {}, 1, 0),

    IRETURN((short) 172, "IRETURN", (short) 0, new short[] {}, 1, 0),

    LRETURN((short) 173, "LRETURN", (short) 0, new short[] {}, 2, 0),

    FRETURN((short) 174, "FRETURN", (short) 0, new short[] {}, 1, 0),

    DRETURN((short) 175, "DRETURN", (short) 0, new short[] {}, 2, 0),

    ARETURN((short) 176, "ARETURN", (short) 0, new short[] {}, 1, 0),

    RETURN((short) 177, "RETURN", (short) 0, new short[] { Const.T_SHORT }, 0, 0),

    GETSTATIC((short) 178, "GETSTATIC", (short) 2, new short[] { Const.T_SHORT }, 0, Const.UNPREDICTABLE),

    PUTSTATIC((short) 179, "PUTSTATIC", (short) 2, new short[] { Const.T_SHORT }, Const.UNPREDICTABLE, 0),

    GETFIELD((short) 180, "GETFIELD", (short) 2, new short[] { Const.T_SHORT }, 1, Const.UNPREDICTABLE),

    PUTFIELD((short) 181, "PUTFIELD", (short) 2, new short[] { Const.T_SHORT }, Const.UNPREDICTABLE, 0),

    INVOKEVIRTUAL((short) 182, "INVOKEVIRTUAL", (short) 2, new short[] { Const.T_SHORT }, Const.UNPREDICTABLE, Const.UNPREDICTABLE),

    INVOKESPECIAL((short) 183, "INVOKESPECIAL", (short) 2, new short[] { Const.T_SHORT }, Const.UNPREDICTABLE, Const.UNPREDICTABLE),

    INVOKENONVIRTUAL((short) 183, "INVOKENONVIRTUAL", (short) 2, new short[] { Const.T_SHORT }, Const.UNPREDICTABLE, Const.UNPREDICTABLE),

    // Old name in JDK 1.0
    INVOKESTATIC((short) 184, "INVOKESTATIC", (short) 2, new short[] { Const.T_SHORT }, Const.UNPREDICTABLE, Const.UNPREDICTABLE),

    INVOKEINTERFACE((short) 185, "INVOKEINTERFACE", (short) 4, new short[] { Const.T_SHORT, Const.T_BYTE, Const.T_BYTE }, Const.UNPREDICTABLE, Const.UNPREDICTABLE),

    INVOKEDYNAMIC((short) 186, "INVOKEDYNAMIC", (short) 4, new short[] { Const.T_SHORT, Const.T_BYTE, Const.T_BYTE }, Const.UNPREDICTABLE, Const.UNPREDICTABLE),

    NEW((short) 187, "NEW", (short) 2, new short[] { Const.T_SHORT }, 0, 1),

    NEWARRAY((short) 188, "NEWARRAY", (short) 1, new short[] { Const.T_BYTE }, 1, 1),

    ANEWARRAY((short) 189, "ANEWARRAY", (short) 2, new short[] { Const.T_SHORT }, 1, 1),

    ARRAYLENGTH((short) 190, "ARRAYLENGTH", (short) 0, new short[] {}, 1, 1),

    ATHROW((short) 191, "ATHROW", (short) 0, new short[] {}, 1, 1),

    CHECKCAST((short) 192, "CHECKCAST", (short) 2, new short[] { Const.T_SHORT }, 1, 1),

    INSTANCEOF((short) 193, "INSTANCEOF", (short) 2, new short[] { Const.T_SHORT }, 1, 1),

    MONITORENTER((short) 194, "MONITORENTER", (short) 0, new short[] {}, 1, 0),

    MONITOREXIT((short) 195, "MONITOREXIT", (short) 0, new short[] {}, 1, 0),

    WIDE((short) 196, "WIDE", Const.UNPREDICTABLE, new short[] { Const.T_SHORT, Const.T_BYTE }, 0, 0),

    MULTIANEWARRAY((short) 197, "MULTIANEWARRAY", (short) 3, new short[] { Const.T_SHORT, Const.T_BYTE }, Const.UNPREDICTABLE, 1),

    IFNULL((short) 198, "IFNULL", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    IFNONNULL((short) 199, "IFNONNULL", (short) 2, new short[] { Const.T_SHORT }, 1, 0),

    GOTO_W((short) 200, "GOTO_W", (short) 4, new short[] { Const.T_INT }, 0, 0),

    JSR_W((short) 201, "JSR_W", (short) 4, new short[] { Const.T_INT }, 0, 1),

    BREAKPOINT((short) 202, "BREAKPOINT", (short) 0, new short[] {}, 0, 0),

    LDC_QUICK((short) 203, "LDC_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    LDC_W_QUICK((short) 204, "LDC_W_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    LDC2_W_QUICK((short) 205, "LDC2_W_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    GETFIELD_QUICK((short) 206, "GETFIELD_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    PUTFIELD_QUICK((short) 207, "PUTFIELD_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    GETFIELD2_QUICK((short) 208, "GETFIELD2_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    PUTFIELD2_QUICK((short) 209, "PUTFIELD2_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    GETSTATIC_QUICK((short) 210, "GETSTATIC_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    PUTSTATIC_QUICK((short) 211, "PUTSTATIC_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    GETSTATIC2_QUICK((short) 212, "GETSTATIC2_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    PUTSTATIC2_QUICK((short) 213, "PUTSTATIC2_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INVOKEVIRTUAL_QUICK((short) 214, "INVOKEVIRTUAL_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INVOKENONVIRTUAL_QUICK((short) 215, "INVOKENONVIRTUAL_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INVOKESUPER_QUICK((short) 216, "INVOKESUPER_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INVOKESTATIC_QUICK((short) 217, "INVOKESTATIC_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INVOKEINTERFACE_QUICK((short) 218, "INVOKEINTERFACE_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INVOKEVIRTUALOBJECT_QUICK((short) 219, "INVOKEVIRTUALOBJECT_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    NEW_QUICK((short) 221, "NEW_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    ANEWARRAY_QUICK((short) 222, "ANEWARRAY_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    MULTIANEWARRAY_QUICK((short) 223, "MULTIANEWARRAY_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    CHECKCAST_QUICK((short) 224, "CHECKCAST_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INSTANCEOF_QUICK((short) 225, "INSTANCEOF_QUICK", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    INVOKEVIRTUAL_QUICK_W((short) 226, "INVOKEVIRTUAL_QUICK_W", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    GETFIELD_QUICK_W((short) 227, "GETFIELD_QUICK_W", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    PUTFIELD_QUICK_W((short) 228, "PUTFIELD_QUICK_W", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    IMPDEP1((short) 254, "IMPDEP1", Const.RESERVED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    IMPDEP2((short) 255, "IMPDEP2", Const.RESERVED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    PUSH((short) 4711, "PUSH", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    SWITCH((short) 4712, "SWITCH", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED),

    UNDEFINED((short) -1, "UNDEFINED", Const.UNDEFINED, new short[] {}, Const.UNDEFINED, Const.UNDEFINED);

    private short tag;
    private String name;
    private short numberOfOperands;
    private short[] typeOfOperands;
    private int consumeStack;
    private int produceStack;

    ClassFileInstructions(short tag, String name, short numberOfOperands, short[] typeOfOperands, int consumeStack, int produceStack) {
        this.tag = tag;
        this.name = name;
        this.numberOfOperands = numberOfOperands;
        this.typeOfOperands = typeOfOperands;
        this.consumeStack = consumeStack;
        this.produceStack = produceStack;
    }

    public int getConsumeStack() {
        return consumeStack;
    }

    public String getName() {
        return name;
    }

    public short getNumberOfOperands() {
        return numberOfOperands;
    }

    public int getProduceStack() {
        return produceStack;
    }

    public short getTag() {
        return tag;
    }

    public short[] getTypeOfOperands() {
        return typeOfOperands;
    }

    public static ClassFileInstructions read(short tag) throws IOException {
        for (ClassFileInstructions v : ClassFileInstructions.values()) {
            if (v.getTag() == tag) {
                return v;
            }
        }
        return UNDEFINED;
    }
}
