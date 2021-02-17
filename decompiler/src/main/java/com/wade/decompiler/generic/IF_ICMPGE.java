package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ICMPGE extends IfInstruction {
    public IF_ICMPGE() {
    }

    public IF_ICMPGE(InstructionHandle target) {
        super(InstructionOpCodes.IF_ICMPGE, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPGE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLT(super.getTarget());
    }
}
