package com.wade.decompiler.generic;

public class FRETURN extends ReturnInstruction {
    public FRETURN() {
        super(com.wade.decompiler.Const.FRETURN);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitFRETURN(this);
    }
}
