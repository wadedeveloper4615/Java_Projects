package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ICMPNE extends IfInstruction {
    public IF_ICMPNE() {
    }

    public IF_ICMPNE(InstructionHandle target) {
        super(InstructionOpCodes.IF_ICMPNE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPEQ(super.getTarget());
    }
}
