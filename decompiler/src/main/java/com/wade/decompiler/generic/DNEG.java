package com.wade.decompiler.generic;

public class DNEG extends ArithmeticInstruction {
    public DNEG() {
        super(com.wade.decompiler.Const.DNEG);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDNEG(this);
    }
}
