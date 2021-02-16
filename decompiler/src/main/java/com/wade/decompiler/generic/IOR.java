package com.wade.decompiler.generic;

public class IOR extends ArithmeticInstruction {
    public IOR() {
        super(com.wade.decompiler.Const.IOR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIOR(this);
    }
}
