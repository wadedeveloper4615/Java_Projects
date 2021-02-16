package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;

public abstract class ReturnInstruction extends Instruction implements ExceptionThrower, TypedInstruction, StackConsumer {
    ReturnInstruction() {
    }

    protected ReturnInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }

    public Type getType() {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case Const.IRETURN:
                return Type.INT;
            case Const.LRETURN:
                return Type.LONG;
            case Const.FRETURN:
                return Type.FLOAT;
            case Const.DRETURN:
                return Type.DOUBLE;
            case Const.ARETURN:
                return Type.OBJECT;
            case Const.RETURN:
                return Type.VOID;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return getType();
    }
}
