package com.wade.decompiler.generic.base;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;

public abstract class ConversionInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    ConversionInstruction() {
    }

    protected ConversionInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case Const.D2I:
            case Const.F2I:
            case Const.L2I:
                return Type.INT;
            case Const.D2F:
            case Const.I2F:
            case Const.L2F:
                return Type.FLOAT;
            case Const.D2L:
            case Const.F2L:
            case Const.I2L:
                return Type.LONG;
            case Const.F2D:
            case Const.I2D:
            case Const.L2D:
                return Type.DOUBLE;
            case Const.I2B:
                return Type.BYTE;
            case Const.I2C:
                return Type.CHAR;
            case Const.I2S:
                return Type.SHORT;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }
}
