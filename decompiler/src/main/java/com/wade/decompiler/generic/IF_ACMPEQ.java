package com.wade.decompiler.generic;

public class IF_ACMPEQ extends IfInstruction {
    IF_ACMPEQ() {
    }

    public IF_ACMPEQ(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ACMPEQ, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPNE(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ACMPEQ(this);
    }
}
