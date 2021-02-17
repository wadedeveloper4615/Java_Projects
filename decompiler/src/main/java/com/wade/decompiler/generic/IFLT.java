package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFLT extends IfInstruction {
    public IFLT() {
    }

    public IFLT(final InstructionHandle target) {
        super(Const.IFLT, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(super.getTarget());
    }
}
