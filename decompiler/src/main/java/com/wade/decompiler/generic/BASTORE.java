package com.wade.decompiler.generic;

public class BASTORE extends ArrayInstruction implements StackConsumer {
    public BASTORE() {
        super(com.wade.decompiler.Const.BASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitBASTORE(this);
    }
}
