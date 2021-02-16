package com.wade.decompiler.generic;

public class IFNONNULL extends IfInstruction {
    IFNONNULL() {
    }

    public IFNONNULL(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFNONNULL, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFNULL(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNONNULL(this);
    }
}
