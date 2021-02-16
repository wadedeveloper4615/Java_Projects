package com.wade.decompiler.generic;

public class DASTORE extends ArrayInstruction implements StackConsumer {
    public DASTORE() {
        super(com.wade.decompiler.Const.DASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitDASTORE(this);
    }
}
