package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;

public abstract class ArrayInstruction extends Instruction implements ExceptionThrower, TypedInstruction {
    ArrayInstruction() {
    }

    protected ArrayInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case com.wade.decompiler.Const.IALOAD:
            case com.wade.decompiler.Const.IASTORE:
                return Type.INT;
            case com.wade.decompiler.Const.CALOAD:
            case com.wade.decompiler.Const.CASTORE:
                return Type.CHAR;
            case com.wade.decompiler.Const.BALOAD:
            case com.wade.decompiler.Const.BASTORE:
                return Type.BYTE;
            case com.wade.decompiler.Const.SALOAD:
            case com.wade.decompiler.Const.SASTORE:
                return Type.SHORT;
            case com.wade.decompiler.Const.LALOAD:
            case com.wade.decompiler.Const.LASTORE:
                return Type.LONG;
            case com.wade.decompiler.Const.DALOAD:
            case com.wade.decompiler.Const.DASTORE:
                return Type.DOUBLE;
            case com.wade.decompiler.Const.FALOAD:
            case com.wade.decompiler.Const.FASTORE:
                return Type.FLOAT;
            case com.wade.decompiler.Const.AALOAD:
            case com.wade.decompiler.Const.AASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + _opcode);
        }
    }
}
