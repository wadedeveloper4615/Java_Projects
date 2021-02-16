package com.wade.decompiler.generic;

public class DUP extends StackInstruction implements PushInstruction {
    public DUP() {
        super(com.wade.decompiler.Const.DUP);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitStackInstruction(this);
        v.visitDUP(this);
    }
}
