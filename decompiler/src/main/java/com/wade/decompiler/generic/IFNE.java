package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFNE extends IfInstruction {
    public IFNE() {
    }

    public IFNE(final InstructionHandle target) {
        super(Const.IFNE, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(super.getTarget());
    }
}
