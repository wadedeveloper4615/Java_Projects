package com.wade.decompiler.generic;

public class BREAKPOINT extends Instruction {
    public BREAKPOINT() {
        super(com.wade.decompiler.Const.BREAKPOINT, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitBREAKPOINT(this);
    }
}
