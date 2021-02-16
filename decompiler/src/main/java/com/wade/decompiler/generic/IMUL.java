package com.wade.decompiler.generic;

public class IMUL extends ArithmeticInstruction {
    public IMUL() {
        super(com.wade.decompiler.Const.IMUL);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIMUL(this);
    }
}
