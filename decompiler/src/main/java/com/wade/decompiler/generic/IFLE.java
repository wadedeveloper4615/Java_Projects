package com.wade.decompiler.generic;

public class IFLE extends IfInstruction {
    IFLE() {
    }

    public IFLE(final InstructionHandle target) {
        super(com.wade.decompiler.Const.IFLE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFGT(super.getTarget());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLE(this);
    }
}
