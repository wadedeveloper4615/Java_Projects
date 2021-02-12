package org.apache.bcel.generic.base;

import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.gen.ConstantPoolGen;

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
