package com.wade.decompiler.generic;

public class IFGE extends IfInstruction {
    IFGE() {
    }

    public IFGE(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFGE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFLT(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFGE(this);
    }
}
