package com.wade.decompiler.generic;

public class IF_ICMPLE extends IfInstruction {
    IF_ICMPLE() {
    }

    public IF_ICMPLE(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ICMPLE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGT(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLE(this);
    }
}
