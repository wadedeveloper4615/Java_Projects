package com.wade.decompiler.generic;

public class RETURN extends ReturnInstruction {
    public RETURN() {
        super(com.wade.decompiler.Const.RETURN);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitRETURN(this);
    }
}
