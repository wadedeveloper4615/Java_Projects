package com.wade.decompiler.generic;

public class NOP extends Instruction {
    public NOP() {
        super(com.wade.decompiler.Const.NOP, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitNOP(this);
    }
}
