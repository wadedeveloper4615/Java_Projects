package com.wade.decompiler.generic;

public class ARETURN extends ReturnInstruction {
    public ARETURN() {
        super(com.wade.decompiler.Const.ARETURN);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitARETURN(this);
    }
}
