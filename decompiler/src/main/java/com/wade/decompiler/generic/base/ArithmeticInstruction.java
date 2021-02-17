package com.wade.decompiler.generic.base;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.type.Type;

public abstract class ArithmeticInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public ArithmeticInstruction() {
    }

    public ArithmeticInstruction(InstructionOpCodes opcode) {
        super(opcode, 1);
    }

    @Override
    public Type getType(ConstantPoolGen cp) {
        InstructionOpCodes _opcode = super.getOpcode();
        switch (_opcode) {
            case DADD:
            case DDIV:
            case DMUL:
            case DNEG:
            case DREM:
            case DSUB:
                return Type.DOUBLE;
            case FADD:
            case FDIV:
            case FMUL:
            case FNEG:
            case FREM:
            case FSUB:
                return Type.FLOAT;
            case IADD:
            case IAND:
            case IDIV:
            case IMUL:
            case INEG:
            case IOR:
            case IREM:
            case ISHL:
            case ISHR:
            case ISUB:
            case IUSHR:
            case IXOR:
                return Type.INT;
            case LADD:
            case LAND:
            case LDIV:
            case LMUL:
            case LNEG:
            case LOR:
            case LREM:
            case LSHL:
            case LSHR:
            case LSUB:
            case LUSHR:
            case LXOR:
                return Type.LONG;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }
}
