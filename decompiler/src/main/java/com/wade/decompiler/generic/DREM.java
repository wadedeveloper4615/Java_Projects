package com.wade.decompiler.generic;

public class DREM extends ArithmeticInstruction {
    public DREM() {
        super(com.wade.decompiler.Const.DREM);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDREM(this);
    }
}
