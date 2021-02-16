package com.wade.decompiler.generic;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {
    GotoInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }

    GotoInstruction() {
    }
}
