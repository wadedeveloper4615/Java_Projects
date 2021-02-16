package com.wade.decompiler.generic;

public class FDIV extends ArithmeticInstruction {
    public FDIV() {
        super(com.wade.decompiler.Const.FDIV);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFDIV(this);
    }
}
