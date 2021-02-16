package com.wade.decompiler.generic;

public class F2L extends ConversionInstruction {
    public F2L() {
        super(com.wade.decompiler.Const.F2L);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitF2L(this);
    }
}
