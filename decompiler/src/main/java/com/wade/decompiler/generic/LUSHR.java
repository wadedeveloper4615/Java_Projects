package com.wade.decompiler.generic;

public class LUSHR extends ArithmeticInstruction {
    public LUSHR() {
        super(com.wade.decompiler.Const.LUSHR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLUSHR(this);
    }
}
