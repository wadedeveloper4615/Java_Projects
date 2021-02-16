package com.wade.decompiler.generic;

public class I2D extends ConversionInstruction {
    public I2D() {
        super(com.wade.decompiler.Const.I2D);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2D(this);
    }
}
