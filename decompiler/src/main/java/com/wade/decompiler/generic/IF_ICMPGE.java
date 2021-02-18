package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ICMPGE extends IfInstruction {
    public IF_ICMPGE() {
    }

    public IF_ICMPGE(InstructionHandle target) {
        super(InstructionOpCodes.IF_ICMPGE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLT(super.getTarget());
    }
}
