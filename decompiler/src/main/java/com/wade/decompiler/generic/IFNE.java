package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFNE extends IfInstruction {
    public IFNE() {
    }

    public IFNE(InstructionHandle target) {
        super(InstructionOpCodes.IFNE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(super.getTarget());
    }
}
