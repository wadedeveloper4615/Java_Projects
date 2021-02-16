package com.wade.decompiler.generic;

public class IASTORE extends ArrayInstruction implements StackConsumer {
    public IASTORE() {
        super(com.wade.decompiler.Const.IASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitIASTORE(this);
    }
}
