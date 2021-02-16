package com.wade.decompiler.generic;

public class IF_ICMPGE extends IfInstruction {
    IF_ICMPGE() {
    }

    public IF_ICMPGE(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ICMPGE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLT(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPGE(this);
    }
}
