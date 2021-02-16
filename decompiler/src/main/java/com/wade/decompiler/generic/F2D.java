package com.wade.decompiler.generic;

public class F2D extends ConversionInstruction {
    public F2D() {
        super(com.wade.decompiler.Const.F2D);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitF2D(this);
    }
}
