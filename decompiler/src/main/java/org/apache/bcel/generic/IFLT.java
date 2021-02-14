package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFLT extends IfInstruction {
    public IFLT() {
    }

    public IFLT(final InstructionHandle target) {
        super(InstructionOpCodes.IFLT, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(super.getTarget());
    }
}
