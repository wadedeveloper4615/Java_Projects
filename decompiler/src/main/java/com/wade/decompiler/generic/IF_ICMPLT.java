package com.wade.decompiler.generic;

public class IF_ICMPLT extends IfInstruction {
    IF_ICMPLT() {
    }

    public IF_ICMPLT(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ICMPLT, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGE(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLT(this);
    }
}
