package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFGT extends IfInstruction {
    public IFGT() {
    }

    public IFGT(final InstructionHandle target) {
        super(InstructionOpCodes.IFGT, target);
    }

    @Override
    public IfInstruction negate() {
        return new IFLE(super.getTarget());
    }
}
