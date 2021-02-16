package com.wade.decompiler.generic;

public class LAND extends ArithmeticInstruction {
    public LAND() {
        super(com.wade.decompiler.Const.LAND);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLAND(this);
    }
}
