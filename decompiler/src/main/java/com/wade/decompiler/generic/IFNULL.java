package com.wade.decompiler.generic;

public class IFNULL extends IfInstruction {
    IFNULL() {
    }

    public IFNULL(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFNULL, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFNONNULL(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNULL(this);
    }
}
