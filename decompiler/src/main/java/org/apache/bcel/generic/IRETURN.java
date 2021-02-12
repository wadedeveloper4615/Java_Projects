package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ReturnInstruction;
import org.apache.bcel.generic.base.Visitor;

public class IRETURN extends ReturnInstruction {
    public IRETURN() {
        super(org.apache.bcel.Const.IRETURN);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitIRETURN(this);
    }
}
