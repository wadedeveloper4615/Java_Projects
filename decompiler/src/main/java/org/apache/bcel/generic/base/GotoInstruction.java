package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.control.InstructionHandle;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {
    public GotoInstruction() {
    }

    protected GotoInstruction(InstructionOpCodes opcode, final InstructionHandle target) {
        super(opcode, target);
    }
}
