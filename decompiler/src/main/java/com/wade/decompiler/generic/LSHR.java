package com.wade.decompiler.generic;

public class LSHR extends ArithmeticInstruction {
    public LSHR() {
        super(com.wade.decompiler.Const.LSHR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLSHR(this);
    }
}
