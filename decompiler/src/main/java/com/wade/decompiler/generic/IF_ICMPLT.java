package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ICMPLT extends IfInstruction {
    public IF_ICMPLT() {
    }

    public IF_ICMPLT(InstructionHandle target) {
        super(InstructionOpCodes.IF_ICMPLT, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGE(super.getTarget());
    }
}
