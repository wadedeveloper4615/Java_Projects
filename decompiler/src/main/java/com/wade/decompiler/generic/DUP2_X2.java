package com.wade.decompiler.generic;

public class DUP2_X2 extends StackInstruction {
    public DUP2_X2() {
        super(com.wade.decompiler.Const.DUP2_X2);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP2_X2(this);
    }
}
