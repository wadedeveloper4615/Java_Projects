package com.wade.decompiler.generic;

public class D2F extends ConversionInstruction {
    public D2F() {
        super(com.wade.decompiler.Const.D2F);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitD2F(this);
    }
}
