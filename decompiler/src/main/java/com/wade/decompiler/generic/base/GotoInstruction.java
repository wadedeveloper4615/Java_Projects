package com.wade.decompiler.generic.base;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {
    public GotoInstruction() {
    }

    public GotoInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }
}
