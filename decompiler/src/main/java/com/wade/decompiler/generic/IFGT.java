package com.wade.decompiler.generic;

public class IFGT extends IfInstruction {
    IFGT() {
    }

    public IFGT(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFGT, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFLE(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFGT(this);
    }
}
