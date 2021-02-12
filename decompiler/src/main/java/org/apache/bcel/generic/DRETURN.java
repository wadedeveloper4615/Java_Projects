package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ReturnInstruction;
import org.apache.bcel.generic.base.Visitor;

public class DRETURN extends ReturnInstruction {
    public DRETURN() {
        super(org.apache.bcel.Const.DRETURN);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitDRETURN(this);
    }
}
