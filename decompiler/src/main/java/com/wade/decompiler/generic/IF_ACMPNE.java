package com.wade.decompiler.generic;

public class IF_ACMPNE extends IfInstruction {
    IF_ACMPNE() {
    }

    public IF_ACMPNE(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ACMPNE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPEQ(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ACMPNE(this);
    }
}
