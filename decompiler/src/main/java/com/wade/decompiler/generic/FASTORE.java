package com.wade.decompiler.generic;

public class FASTORE extends ArrayInstruction implements StackConsumer {
    public FASTORE() {
        super(com.wade.decompiler.Const.FASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitFASTORE(this);
    }
}
