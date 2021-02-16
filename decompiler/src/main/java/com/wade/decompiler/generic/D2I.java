package com.wade.decompiler.generic;

public class D2I extends ConversionInstruction {
    public D2I() {
        super(com.wade.decompiler.Const.D2I);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitD2I(this);
    }
}
