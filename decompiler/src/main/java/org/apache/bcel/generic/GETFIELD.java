package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.FieldInstruction;
import org.apache.bcel.generic.base.StackConsumer;

public class GETFIELD extends FieldInstruction implements ExceptionThrower, StackConsumer, StackProducer {
    public GETFIELD() {
    }

    public GETFIELD(final int index) {
        super(InstructionOpCodes.GETFIELD, index);
    }

//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitExceptionThrower(this);
//        v.visitStackConsumer(this);
//        v.visitStackProducer(this);
//        v.visitTypedInstruction(this);
//        v.visitLoadClass(this);
//        v.visitCPInstruction(this);
//        v.visitFieldOrMethod(this);
//        v.visitFieldInstruction(this);
//        v.visitGETFIELD(this);
//    }
//
    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
//
//    @Override
//    public int produceStack(final ConstantPoolGen cpg) {
//        return getFieldSize(cpg);
//    }
}
