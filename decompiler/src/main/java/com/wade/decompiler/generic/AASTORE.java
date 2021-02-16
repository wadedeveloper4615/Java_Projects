package com.wade.decompiler.generic;

public class AASTORE extends ArrayInstruction implements StackConsumer {
    public AASTORE() {
        super(com.wade.decompiler.Const.AASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitAASTORE(this);
    }
}
