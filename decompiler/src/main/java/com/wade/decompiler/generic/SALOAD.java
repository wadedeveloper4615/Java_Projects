package com.wade.decompiler.generic;

public class SALOAD extends ArrayInstruction implements StackProducer {
    public SALOAD() {
        super(com.wade.decompiler.Const.SALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitSALOAD(this);
    }
}
