package com.wade.decompiler.generic;

public class ISUB extends ArithmeticInstruction {
    public ISUB() {
        super(com.wade.decompiler.Const.ISUB);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitISUB(this);
    }
}
