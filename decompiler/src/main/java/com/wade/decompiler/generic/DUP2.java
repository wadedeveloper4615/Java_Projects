package com.wade.decompiler.generic;

public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2() {
        super(com.wade.decompiler.Const.DUP2);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitStackInstruction(this);
        v.visitDUP2(this);
    }
}
