package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFGE extends IfInstruction {
    public IFGE() {
    }

    public IFGE(final InstructionHandle target) {
        super(Const.IFGE, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFGE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFLT(super.getTarget());
    }
}
