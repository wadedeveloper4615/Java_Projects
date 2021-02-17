package com.wade.decompiler.generic.base;

import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
    public IfInstruction() {
    }

    public IfInstruction(InstructionOpCodes opcode, InstructionHandle target) {
        super(opcode, target);
    }

    public abstract IfInstruction negate();
}
