package com.wade.decompiler.generic;

public class FALOAD extends ArrayInstruction implements StackProducer {
    public FALOAD() {
        super(com.wade.decompiler.Const.FALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitFALOAD(this);
    }
}
