package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;

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
