package com.wade.decompiler.generic;

public class BALOAD extends ArrayInstruction implements StackProducer {
    public BALOAD() {
        super(com.wade.decompiler.Const.BALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitBALOAD(this);
    }
}
