package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFLE extends IfInstruction {
    public IFLE() {
    }

    public IFLE(InstructionHandle target) {
        super(InstructionOpCodes.IFLE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFGT(super.getTarget());
    }
}
