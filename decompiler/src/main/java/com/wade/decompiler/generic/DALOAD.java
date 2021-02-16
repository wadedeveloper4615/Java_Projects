package com.wade.decompiler.generic;

public class DALOAD extends ArrayInstruction implements StackProducer {
    public DALOAD() {
        super(com.wade.decompiler.Const.DALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitDALOAD(this);
    }
}
