package com.wade.decompiler.generic;

public class INEG extends ArithmeticInstruction {
    public INEG() {
        super(com.wade.decompiler.Const.INEG);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitINEG(this);
    }
}