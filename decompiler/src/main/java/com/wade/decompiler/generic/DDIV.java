package com.wade.decompiler.generic;

public class DDIV extends ArithmeticInstruction {
    public DDIV() {
        super(com.wade.decompiler.Const.DDIV);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDDIV(this);
    }
}
