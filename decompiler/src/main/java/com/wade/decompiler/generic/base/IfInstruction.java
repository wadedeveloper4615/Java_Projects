package com.wade.decompiler.generic.base;

public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
    public IfInstruction() {
    }

    public IfInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }

    public abstract IfInstruction negate();
}
