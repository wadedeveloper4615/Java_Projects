package com.wade.decompiler.generic.base;

import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {
    public GotoInstruction() {
    }

    public GotoInstruction(InstructionOpCodes opcode, InstructionHandle target) {
        super(opcode, target);
    }
}
