package com.wade.decompiler.generic;

public class IF_ICMPEQ extends IfInstruction {
    IF_ICMPEQ() {
    }

    public IF_ICMPEQ(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IF_ICMPEQ, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPNE(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPEQ(this);
    }
}
