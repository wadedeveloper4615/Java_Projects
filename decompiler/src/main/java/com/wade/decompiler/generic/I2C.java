package com.wade.decompiler.generic;

public class I2C extends ConversionInstruction {
    public I2C() {
        super(com.wade.decompiler.Const.I2C);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2C(this);
    }
}
