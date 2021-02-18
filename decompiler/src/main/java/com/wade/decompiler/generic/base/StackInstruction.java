package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.Type;

public abstract class StackInstruction extends Instruction {
    public StackInstruction() {
    }

    public StackInstruction(InstructionOpCodes opcode) {
        super(opcode, 1);
    }

    public Type getType(ConstantPool cp) {
        return Type.UNKNOWN;
    }
}
