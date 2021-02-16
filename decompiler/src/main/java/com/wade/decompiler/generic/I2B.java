package com.wade.decompiler.generic;

public class I2B extends ConversionInstruction {
    public I2B() {
        super(com.wade.decompiler.Const.I2B);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2B(this);
    }
}
