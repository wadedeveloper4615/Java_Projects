package com.wade.decompiler.generic;

public class CALOAD extends ArrayInstruction implements StackProducer {
    public CALOAD() {
        super(com.wade.decompiler.Const.CALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitCALOAD(this);
    }
}
