package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.FieldInstruction;
import org.apache.bcel.generic.base.PopInstruction;

public class PUTFIELD extends FieldInstruction implements PopInstruction, ExceptionThrower {
    public PUTFIELD() {
    }

    public PUTFIELD(final int index) {
        super(InstructionOpCodes.PUTFIELD, index);
    }
//    @Override
//    public void accept(final Visitor v) {
//        v.visitExceptionThrower(this);
//        v.visitStackConsumer(this);
//        v.visitPopInstruction(this);
//        v.visitTypedInstruction(this);
//        v.visitLoadClass(this);
//        v.visitCPInstruction(this);
//        v.visitFieldOrMethod(this);
//        v.visitFieldInstruction(this);
//        v.visitPUTFIELD(this);
//    }
//
//    @Override
//    public int consumeStack(final ConstantPoolGen cpg) {
//        return getFieldSize(cpg) + 1;
//    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
