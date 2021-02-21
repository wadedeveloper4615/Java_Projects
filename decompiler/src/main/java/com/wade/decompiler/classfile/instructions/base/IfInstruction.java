package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
    public IfInstruction() {
    }

    public IfInstruction(InstructionOpCodes opcode, InstructionHandle target, ConstantPool constantPool) {
        super(opcode, target, constantPool);
    }

    public abstract IfInstruction negate();
}
