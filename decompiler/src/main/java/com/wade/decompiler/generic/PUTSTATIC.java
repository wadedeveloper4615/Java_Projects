package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;

public class PUTSTATIC extends FieldInstruction implements ExceptionThrower, PopInstruction {
    PUTSTATIC() {
    }

    public PUTSTATIC(final int index) {
        super(Const.PUTSTATIC, index);
    }

    @Override
    public int consumeStack(final ConstantPoolGen cpg) {
        return getFieldSize(cpg);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitFieldInstruction(this);
        v.visitPUTSTATIC(this);
    }
}
