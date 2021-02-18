package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ACMPEQ extends IfInstruction {
    public IF_ACMPEQ() {
    }

    public IF_ACMPEQ(InstructionHandle target) {
        super(InstructionOpCodes.IF_ACMPEQ, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPNE(super.getTarget());
    }
}
