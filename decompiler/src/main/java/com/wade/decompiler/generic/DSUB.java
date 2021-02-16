package com.wade.decompiler.generic;

public class DSUB extends ArithmeticInstruction {
    public DSUB() {
        super(com.wade.decompiler.Const.DSUB);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDSUB(this);
    }
}
