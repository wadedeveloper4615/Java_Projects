package com.wade.decompiler.generic;

public class IF_ICMPGT extends IfInstruction {
    IF_ICMPGT() {
    }

    public IF_ICMPGT(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ICMPGT, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLE(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPGT(this);
    }
}
