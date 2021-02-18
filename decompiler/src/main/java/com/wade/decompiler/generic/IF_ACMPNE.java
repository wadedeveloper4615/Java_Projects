package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ACMPNE extends IfInstruction {
    public IF_ACMPNE() {
    }

    public IF_ACMPNE(InstructionHandle target) {
        super(InstructionOpCodes.IF_ACMPNE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPEQ(super.getTarget());
    }
}
