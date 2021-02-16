package com.wade.decompiler.generic;

public class LSUB extends ArithmeticInstruction {
    public LSUB() {
        super(com.wade.decompiler.Const.LSUB);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLSUB(this);
    }
}
