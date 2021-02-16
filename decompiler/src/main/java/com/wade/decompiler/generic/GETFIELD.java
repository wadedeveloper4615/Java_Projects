package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;

public class GETFIELD extends FieldInstruction implements ExceptionThrower, StackConsumer, StackProducer {
    GETFIELD() {
    }

    public GETFIELD(final int index) {
        super(Const.GETFIELD, index);
    }

    @Override
    public int produceStack(final ConstantPoolGen cpg) {
        return getFieldSize(cpg);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitFieldInstruction(this);
        v.visitGETFIELD(this);
    }
}
