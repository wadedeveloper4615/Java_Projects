package com.wade.decompiler.generic;

public class IAND extends ArithmeticInstruction {
    public IAND() {
        super(com.wade.decompiler.Const.IAND);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIAND(this);
    }
}
