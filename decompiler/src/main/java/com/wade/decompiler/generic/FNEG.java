package com.wade.decompiler.generic;

public class FNEG extends ArithmeticInstruction {
    public FNEG() {
        super(com.wade.decompiler.Const.FNEG);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFNEG(this);
    }
}
