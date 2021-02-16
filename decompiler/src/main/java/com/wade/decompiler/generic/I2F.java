package com.wade.decompiler.generic;

public class I2F extends ConversionInstruction {
    public I2F() {
        super(com.wade.decompiler.Const.I2F);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2F(this);
    }
}
