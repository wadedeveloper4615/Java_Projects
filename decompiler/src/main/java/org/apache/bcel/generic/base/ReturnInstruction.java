package org.apache.bcel.generic.base;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.Type;

public abstract class ReturnInstruction extends Instruction implements ExceptionThrower, TypedInstruction, StackConsumer {
    ReturnInstruction() {
    }

    protected ReturnInstruction(InstructionOpCodes opcode) {
        super(opcode, (short) 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }

    public Type getType() {
        InstructionOpCodes _opcode = super.getOpcode();
        switch (_opcode) {
            case IRETURN:
                return Type.INT;
            case LRETURN:
                return Type.LONG;
            case FRETURN:
                return Type.FLOAT;
            case DRETURN:
                return Type.DOUBLE;
            case ARETURN:
                return Type.OBJECT;
            case RETURN:
                return Type.VOID;
            default:
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }
//
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        return getType();
//    }
}
