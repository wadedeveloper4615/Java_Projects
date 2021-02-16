package com.wade.decompiler.generic;

public class L2I extends ConversionInstruction {
    public L2I() {
        super(com.wade.decompiler.Const.L2I);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitL2I(this);
    }
}
