package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class ReturnInstruction extends Instruction implements ExceptionThrower, TypedInstruction, StackConsumer {
    public ReturnInstruction() {
    }

    public ReturnInstruction(InstructionOpCodes opcode, ConstantPool cp) {
        super(opcode, 1, cp);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }

    @Override
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
}