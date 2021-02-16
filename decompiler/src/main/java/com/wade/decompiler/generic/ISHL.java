package com.wade.decompiler.generic;

public class ISHL extends ArithmeticInstruction {
    public ISHL() {
        super(com.wade.decompiler.Const.ISHL);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitISHL(this);
    }
}
