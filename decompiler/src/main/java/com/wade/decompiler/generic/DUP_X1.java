package com.wade.decompiler.generic;

public class DUP_X1 extends StackInstruction {
    public DUP_X1() {
        super(com.wade.decompiler.Const.DUP_X1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP_X1(this);
    }
}
