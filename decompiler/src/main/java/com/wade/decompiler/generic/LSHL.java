package com.wade.decompiler.generic;

public class LSHL extends ArithmeticInstruction {
    public LSHL() {
        super(com.wade.decompiler.Const.LSHL);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLSHL(this);
    }
}
