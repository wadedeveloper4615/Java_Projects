package com.wade.decompiler.generic;

public class L2D extends ConversionInstruction {
    public L2D() {
        super(com.wade.decompiler.Const.L2D);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitL2D(this);
    }
}
