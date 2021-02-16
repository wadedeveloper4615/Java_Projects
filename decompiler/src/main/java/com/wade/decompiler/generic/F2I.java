package com.wade.decompiler.generic;

public class F2I extends ConversionInstruction {
    public F2I() {
        super(com.wade.decompiler.Const.F2I);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitF2I(this);
    }
}
