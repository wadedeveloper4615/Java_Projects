package com.wade.decompiler.generic;

public class IADD extends ArithmeticInstruction {
    public IADD() {
        super(com.wade.decompiler.Const.IADD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIADD(this);
    }
}
