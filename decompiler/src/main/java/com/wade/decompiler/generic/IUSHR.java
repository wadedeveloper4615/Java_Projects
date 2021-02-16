package com.wade.decompiler.generic;

public class IUSHR extends ArithmeticInstruction {
    public IUSHR() {
        super(com.wade.decompiler.Const.IUSHR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIUSHR(this);
    }
}
