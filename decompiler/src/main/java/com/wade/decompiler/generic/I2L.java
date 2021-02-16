package com.wade.decompiler.generic;

public class I2L extends ConversionInstruction {
    public I2L() {
        super(com.wade.decompiler.Const.I2L);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2L(this);
    }
}
