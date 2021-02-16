package com.wade.decompiler.generic;

public abstract class StackInstruction extends Instruction {
    StackInstruction() {
    }

    protected StackInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    public Type getType(final ConstantPoolGen cp) {
        return Type.UNKNOWN;
    }
}
