package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ICMPGT extends IfInstruction {
    public IF_ICMPGT() {
    }

    public IF_ICMPGT(final InstructionHandle target) {
        super(Const.IF_ICMPGT, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPGT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLE(super.getTarget());
    }
}
