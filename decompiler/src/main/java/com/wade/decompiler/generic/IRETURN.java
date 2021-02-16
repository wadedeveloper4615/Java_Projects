package com.wade.decompiler.generic;

public class IRETURN extends ReturnInstruction {
    public IRETURN() {
        super(com.wade.decompiler.Const.IRETURN);
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
