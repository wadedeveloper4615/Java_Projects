package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.FieldInstruction;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class GETSTATIC extends FieldInstruction implements PushInstruction, ExceptionThrower {
    public GETSTATIC() {
    }

    public GETSTATIC(final int index) {
        super(Const.GETSTATIC, index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitFieldInstruction(this);
        v.visitGETSTATIC(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public int produceStack(final ConstantPoolGen cpg) {
        return getFieldSize(cpg);
    }
}
