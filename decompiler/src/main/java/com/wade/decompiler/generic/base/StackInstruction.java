package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.Type;

public abstract class StackInstruction extends Instruction {
    public StackInstruction() {
    }

    public StackInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 1, constantPool);
    }

    public Type getType(ConstantPool cp) {
        return Type.UNKNOWN;
    }
}
