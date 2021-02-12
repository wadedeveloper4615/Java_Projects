package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ReturnInstruction;
import org.apache.bcel.generic.base.Visitor;

public class LRETURN extends ReturnInstruction {
    public LRETURN() {
        super(org.apache.bcel.Const.LRETURN);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitLRETURN(this);
    }
}
