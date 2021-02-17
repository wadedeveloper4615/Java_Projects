package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ICMPGT extends IfInstruction {
    public IF_ICMPGT() {
    }

    public IF_ICMPGT(InstructionHandle target) {
        super(InstructionOpCodes.IF_ICMPGT, target);
    }

    @Override
    public void accept(Visitor v) {
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
