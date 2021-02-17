package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IF_ICMPNE extends IfInstruction {
    public IF_ICMPNE() {
    }

    public IF_ICMPNE(InstructionHandle target) {
        super(InstructionOpCodes.IF_ICMPNE, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPNE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPEQ(super.getTarget());
    }
}
