package com.wade.decompiler.generic;

public class FSUB extends ArithmeticInstruction {
    public FSUB() {
        super(com.wade.decompiler.Const.FSUB);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFSUB(this);
    }
}
