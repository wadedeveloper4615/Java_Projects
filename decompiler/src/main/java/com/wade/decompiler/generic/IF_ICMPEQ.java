package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ICMPEQ extends IfInstruction {
    public IF_ICMPEQ() {
    }

    public IF_ICMPEQ(final InstructionHandle target) {
        super(Const.IF_ICMPEQ, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPEQ(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPNE(super.getTarget());
    }
}
