package org.apache.bcel.generic.base;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public abstract class ArrayInstruction extends Instruction implements ExceptionThrower, TypedInstruction {
    public ArrayInstruction() {
    }

    public ArrayInstruction(final short opcode) {
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
            case org.apache.bcel.Const.IALOAD:
            case org.apache.bcel.Const.IASTORE:
                return Type.INT;
            case org.apache.bcel.Const.CALOAD:
            case org.apache.bcel.Const.CASTORE:
                return Type.CHAR;
            case org.apache.bcel.Const.BALOAD:
            case org.apache.bcel.Const.BASTORE:
                return Type.BYTE;
            case org.apache.bcel.Const.SALOAD:
            case org.apache.bcel.Const.SASTORE:
                return Type.SHORT;
            case org.apache.bcel.Const.LALOAD:
            case org.apache.bcel.Const.LASTORE:
                return Type.LONG;
            case org.apache.bcel.Const.DALOAD:
            case org.apache.bcel.Const.DASTORE:
                return Type.DOUBLE;
            case org.apache.bcel.Const.FALOAD:
            case org.apache.bcel.Const.FASTORE:
                return Type.FLOAT;
            case org.apache.bcel.Const.AALOAD:
            case org.apache.bcel.Const.AASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + _opcode);
        }
    }
}
