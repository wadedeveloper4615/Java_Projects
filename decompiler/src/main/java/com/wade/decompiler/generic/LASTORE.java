package com.wade.decompiler.generic;

public class LASTORE extends ArrayInstruction implements StackConsumer {
    public LASTORE() {
        super(com.wade.decompiler.Const.LASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitLASTORE(this);
    }
}
