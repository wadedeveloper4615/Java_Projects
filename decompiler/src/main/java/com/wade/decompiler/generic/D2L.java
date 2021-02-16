package com.wade.decompiler.generic;

public class D2L extends ConversionInstruction {
    public D2L() {
        super(com.wade.decompiler.Const.D2L);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitD2L(this);
    }
}
