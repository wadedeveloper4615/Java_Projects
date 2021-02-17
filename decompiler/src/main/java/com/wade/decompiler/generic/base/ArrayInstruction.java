package com.wade.decompiler.generic.base;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;

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
            case Const.IALOAD:
            case Const.IASTORE:
                return Type.INT;
            case Const.CALOAD:
            case Const.CASTORE:
                return Type.CHAR;
            case Const.BALOAD:
            case Const.BASTORE:
                return Type.BYTE;
            case Const.SALOAD:
            case Const.SASTORE:
                return Type.SHORT;
            case Const.LALOAD:
            case Const.LASTORE:
                return Type.LONG;
            case Const.DALOAD:
            case Const.DASTORE:
                return Type.DOUBLE;
            case Const.FALOAD:
            case Const.FASTORE:
                return Type.FLOAT;
            case Const.AALOAD:
            case Const.AASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + _opcode);
        }
    }
}
