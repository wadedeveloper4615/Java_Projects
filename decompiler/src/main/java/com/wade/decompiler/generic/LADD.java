package com.wade.decompiler.generic;

public class LADD extends ArithmeticInstruction {
    public LADD() {
        super(com.wade.decompiler.Const.LADD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLADD(this);
    }
}
