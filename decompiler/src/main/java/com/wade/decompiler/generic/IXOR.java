package com.wade.decompiler.generic;

public class IXOR extends ArithmeticInstruction {
    public IXOR() {
        super(com.wade.decompiler.Const.IXOR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIXOR(this);
    }
}
