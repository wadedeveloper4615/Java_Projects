package com.wade.decompiler.generic;

public class CASTORE extends ArrayInstruction implements StackConsumer {
    public CASTORE() {
        super(com.wade.decompiler.Const.CASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitCASTORE(this);
    }
}
