package com.wade.decompiler.generic;

public class LOR extends ArithmeticInstruction {
    public LOR() {
        super(com.wade.decompiler.Const.LOR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLOR(this);
    }
}
