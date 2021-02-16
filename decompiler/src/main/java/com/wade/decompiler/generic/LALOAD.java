package com.wade.decompiler.generic;

public class LALOAD extends ArrayInstruction implements StackProducer {
    public LALOAD() {
        super(com.wade.decompiler.Const.LALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitLALOAD(this);
    }
}
