package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ACMPEQ extends IfInstruction {
    public IF_ACMPEQ() {
    }

    public IF_ACMPEQ(InstructionHandle target) {
        super(InstructionOpCodes.IF_ACMPEQ, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ACMPEQ(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPNE(super.getTarget());
    }
}
