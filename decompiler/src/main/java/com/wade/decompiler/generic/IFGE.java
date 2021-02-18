package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFGE extends IfInstruction {
    public IFGE() {
    }

    public IFGE(InstructionHandle target) {
        super(InstructionOpCodes.IFGE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFLT(super.getTarget());
    }
}
