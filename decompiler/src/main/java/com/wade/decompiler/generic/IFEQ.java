package com.wade.decompiler.generic;

public class IFEQ extends IfInstruction {
    IFEQ() {
    }

    public IFEQ(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFEQ, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFNE(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFEQ(this);
    }
}
