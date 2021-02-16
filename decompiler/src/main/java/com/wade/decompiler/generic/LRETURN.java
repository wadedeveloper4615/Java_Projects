package com.wade.decompiler.generic;

public class LRETURN extends ReturnInstruction {
    public LRETURN() {
        super(com.wade.decompiler.Const.LRETURN);
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
