package com.wade.decompiler.generic;

public class DRETURN extends ReturnInstruction {
    public DRETURN() {
        super(com.wade.decompiler.Const.DRETURN);
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
