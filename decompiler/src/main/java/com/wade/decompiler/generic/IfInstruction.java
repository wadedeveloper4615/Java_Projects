package com.wade.decompiler.generic;

public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
    IfInstruction() {
    }

    protected IfInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }

    public abstract IfInstruction negate();
}
