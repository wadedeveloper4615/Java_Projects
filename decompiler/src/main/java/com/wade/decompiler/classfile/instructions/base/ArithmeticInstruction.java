package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class ArithmeticInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public ArithmeticInstruction() {
    }

    public ArithmeticInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 1, constantPool);
    }

    @Override
    public Type getType() {
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
