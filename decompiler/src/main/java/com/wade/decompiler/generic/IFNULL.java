package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFNULL extends IfInstruction {
    public IFNULL() {
    }

    public IFNULL(InstructionHandle target) {
        super(InstructionOpCodes.IFNULL, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFNONNULL(super.getTarget());
    }
}
