package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFLE extends IfInstruction {
    public IFLE() {
    }

    public IFLE(final InstructionHandle target) {
        super(InstructionOpCodes.IFLE, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFGT(super.getTarget());
    }
}
