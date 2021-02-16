package com.wade.decompiler.generic;

public class FMUL extends ArithmeticInstruction {
    public FMUL() {
        super(com.wade.decompiler.Const.FMUL);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFMUL(this);
    }
}
