package com.wade.decompiler.generic;

public class IF_ICMPNE extends IfInstruction {
    IF_ICMPNE() {
    }

    public IF_ICMPNE(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ICMPNE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPEQ(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPNE(this);
    }
}