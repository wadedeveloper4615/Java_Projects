package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFEQ extends IfInstruction {
    public IFEQ() {
    }

    public IFEQ(final InstructionHandle target) {
        super(Const.IFEQ, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFEQ(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFNE(super.getTarget());
    }
}
