package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFEQ extends IfInstruction {
    public IFEQ() {
    }

    public IFEQ(InstructionHandle target) {
        super(InstructionOpCodes.IFEQ, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFNE(super.getTarget());
    }
}
