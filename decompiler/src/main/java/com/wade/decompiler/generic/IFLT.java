package com.wade.decompiler.generic;

public class IFLT extends IfInstruction {
    IFLT() {
    }

    public IFLT(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFLT, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLT(this);
    }
}
