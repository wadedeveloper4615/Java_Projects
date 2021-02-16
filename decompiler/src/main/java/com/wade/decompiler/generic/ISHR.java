package com.wade.decompiler.generic;

public class ISHR extends ArithmeticInstruction {
    public ISHR() {
        super(com.wade.decompiler.Const.ISHR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitISHR(this);
    }
}
