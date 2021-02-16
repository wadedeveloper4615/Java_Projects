package com.wade.decompiler.generic;

public class LNEG extends ArithmeticInstruction {
    public LNEG() {
        super(com.wade.decompiler.Const.LNEG);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLNEG(this);
    }
}
