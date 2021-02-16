package com.wade.decompiler.generic;

public class DUP_X2 extends StackInstruction {
    public DUP_X2() {
        super(com.wade.decompiler.Const.DUP_X2);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP_X2(this);
    }
}
