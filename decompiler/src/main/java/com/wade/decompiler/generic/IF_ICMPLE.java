package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ICMPLE extends IfInstruction {
    public IF_ICMPLE() {
    }

    public IF_ICMPLE(InstructionHandle target) {
        super(InstructionOpCodes.IF_ICMPLE, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGT(super.getTarget());
    }
}
