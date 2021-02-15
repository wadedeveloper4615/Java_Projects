package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;

public abstract class StackInstruction extends Instruction {
    StackInstruction() {
    }

    protected StackInstruction(InstructionOpCodes opcode) {
        super(opcode, (short) 1);
    }
//
//    public Type getType(final ConstantPoolGen cp) {
//        return Type.UNKNOWN;
//    }
}
