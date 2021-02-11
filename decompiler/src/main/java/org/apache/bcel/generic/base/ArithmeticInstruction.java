package org.apache.bcel.generic.base;

import org.apache.bcel.Const;
import org.apache.bcel.generic.StackProducer;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public abstract class ArithmeticInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public ArithmeticInstruction() {
    }

    protected ArithmeticInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case Const.DADD:
            case Const.DDIV:
            case Const.DMUL:
            case Const.DNEG:
            case Const.DREM:
            case Const.DSUB:
                return Type.DOUBLE;
            case Const.FADD:
            case Const.FDIV:
            case Const.FMUL:
            case Const.FNEG:
            case Const.FREM:
            case Const.FSUB:
                return Type.FLOAT;
            case Const.IADD:
            case Const.IAND:
            case Const.IDIV:
            case Const.IMUL:
            case Const.INEG:
            case Const.IOR:
            case Const.IREM:
            case Const.ISHL:
            case Const.ISHR:
            case Const.ISUB:
            case Const.IUSHR:
            case Const.IXOR:
                return Type.INT;
            case Const.LADD:
            case Const.LAND:
            case Const.LDIV:
            case Const.LMUL:
            case Const.LNEG:
            case Const.LOR:
            case Const.LREM:
            case Const.LSHL:
            case Const.LSHR:
            case Const.LSUB:
            case Const.LUSHR:
            case Const.LXOR:
                return Type.LONG;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }
}
