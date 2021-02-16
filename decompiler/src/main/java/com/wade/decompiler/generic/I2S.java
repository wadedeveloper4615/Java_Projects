package com.wade.decompiler.generic;

public class I2S extends ConversionInstruction {
    public I2S() {
        super(com.wade.decompiler.Const.I2S);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2S(this);
    }
}