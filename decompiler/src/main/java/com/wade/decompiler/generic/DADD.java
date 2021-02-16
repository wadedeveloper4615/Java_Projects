package com.wade.decompiler.generic;

public class DADD extends ArithmeticInstruction {
    public DADD() {
        super(com.wade.decompiler.Const.DADD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDADD(this);
    }
}
