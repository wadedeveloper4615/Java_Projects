package com.wade.decompiler.generic;

public class SASTORE extends ArrayInstruction implements StackConsumer {
    public SASTORE() {
        super(com.wade.decompiler.Const.SASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitSASTORE(this);
    }
}
