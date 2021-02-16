package com.wade.decompiler.generic;

public class DMUL extends ArithmeticInstruction {
    public DMUL() {
        super(com.wade.decompiler.Const.DMUL);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDMUL(this);
    }
}
