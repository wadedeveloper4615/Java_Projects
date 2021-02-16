package com.wade.decompiler.generic;

public class LMUL extends ArithmeticInstruction {
    public LMUL() {
        super(com.wade.decompiler.Const.LMUL);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLMUL(this);
    }
}
