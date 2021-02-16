package com.wade.decompiler.generic;

public class LXOR extends ArithmeticInstruction {
    public LXOR() {
        super(com.wade.decompiler.Const.LXOR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLXOR(this);
    }
}
