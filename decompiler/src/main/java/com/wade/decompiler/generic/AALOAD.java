package com.wade.decompiler.generic;

public class AALOAD extends ArrayInstruction implements StackProducer {
    public AALOAD() {
        super(com.wade.decompiler.Const.AALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitAALOAD(this);
    }
}
