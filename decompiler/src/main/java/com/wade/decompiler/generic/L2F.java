package com.wade.decompiler.generic;

public class L2F extends ConversionInstruction {
    public L2F() {
        super(com.wade.decompiler.Const.L2F);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitL2F(this);
    }
}
