package com.wade.decompiler.generic;

public class POP extends StackInstruction implements PopInstruction {
    public POP() {
        super(com.wade.decompiler.Const.POP);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitStackInstruction(this);
        v.visitPOP(this);
    }
}
