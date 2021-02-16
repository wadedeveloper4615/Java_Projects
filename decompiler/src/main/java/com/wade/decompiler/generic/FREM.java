package com.wade.decompiler.generic;

public class FREM extends ArithmeticInstruction {
    public FREM() {
        super(com.wade.decompiler.Const.FREM);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFREM(this);
    }
}
