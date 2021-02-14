package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.FieldInstruction;
import org.apache.bcel.generic.base.PushInstruction;

public class GETSTATIC extends FieldInstruction implements PushInstruction, ExceptionThrower {
    public GETSTATIC() {
    }

    public GETSTATIC(final int index) {
        super(InstructionOpCodes.GETSTATIC, index);
    }

//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackProducer(this);
//        v.visitPushInstruction(this);
//        v.visitExceptionThrower(this);
//        v.visitTypedInstruction(this);
//        v.visitLoadClass(this);
//        v.visitCPInstruction(this);
//        v.visitFieldOrMethod(this);
//        v.visitFieldInstruction(this);
//        v.visitGETSTATIC(this);
//    }
//
    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
//
//    @Override
//    public int produceStack(final ConstantPoolGen cpg) {
//        return getFieldSize(cpg);
//    }
}
