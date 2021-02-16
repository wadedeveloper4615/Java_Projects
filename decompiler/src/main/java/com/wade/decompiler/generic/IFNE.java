package com.wade.decompiler.generic;

public class IFNE extends IfInstruction {
    IFNE() {
    }

    public IFNE(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFNE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNE(this);
    }
}
