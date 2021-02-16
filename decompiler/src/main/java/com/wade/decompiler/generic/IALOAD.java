package com.wade.decompiler.generic;

public class IALOAD extends ArrayInstruction implements StackProducer {
    public IALOAD() {
        super(com.wade.decompiler.Const.IALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitIALOAD(this);
    }
}
