package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.StackProducer;

public abstract class ConversionInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    ConversionInstruction() {
    }

    protected ConversionInstruction(InstructionOpCodes opcode) {
        super(opcode, (short) 1);
    }
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        InstructionOpCodes _opcode = super.getOpcode();
//        switch (_opcode) {
//            case D2I:
//            case F2I:
//            case L2I:
//                return Type.INT;
//            case D2F:
//            case I2F:
//            case L2F:
//                return Type.FLOAT;
//            case D2L:
//            case F2L:
//            case I2L:
//                return Type.LONG;
//            case F2D:
//            case I2D:
//            case L2D:
//                return Type.DOUBLE;
//            case I2B:
//                return Type.BYTE;
//            case I2C:
//                return Type.CHAR;
//            case I2S:
//                return Type.SHORT;
//            default:
//                throw new ClassGenException("Unknown type " + _opcode);
//        }
//    }
}
