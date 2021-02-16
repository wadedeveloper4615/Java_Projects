package com.wade.decompiler.generic;

public class FADD extends ArithmeticInstruction {
    public FADD() {
        super(com.wade.decompiler.Const.FADD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFADD(this);
    }
}
