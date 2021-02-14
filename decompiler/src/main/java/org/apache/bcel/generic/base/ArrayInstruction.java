package org.apache.bcel.generic.base;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;

public abstract class ArrayInstruction extends Instruction implements ExceptionThrower, TypedInstruction {
    public ArrayInstruction() {
    }

    public ArrayInstruction(InstructionOpCodes opcode) {
        super(opcode, (short) 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        InstructionOpCodes _opcode = super.getOpcode();
//        switch (_opcode) {
//            case IALOAD:
//            case IASTORE:
//                return Type.INT;
//            case CALOAD:
//            case CASTORE:
//                return Type.CHAR;
//            case BALOAD:
//            case BASTORE:
//                return Type.BYTE;
//            case SALOAD:
//            case SASTORE:
//                return Type.SHORT;
//            case LALOAD:
//            case LASTORE:
//                return Type.LONG;
//            case DALOAD:
//            case DASTORE:
//                return Type.DOUBLE;
//            case FALOAD:
//            case FASTORE:
//                return Type.FLOAT;
//            case AALOAD:
//            case AASTORE:
//                return Type.OBJECT;
//            default:
//                throw new ClassGenException("Unknown case in switch" + _opcode);
//        }
//    }
}
