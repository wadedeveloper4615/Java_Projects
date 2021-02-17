package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ACMPNE extends IfInstruction {
    public IF_ACMPNE() {
    }

    public IF_ACMPNE(final InstructionHandle target) {
        super(Const.IF_ACMPNE, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ACMPNE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPEQ(super.getTarget());
    }
}
