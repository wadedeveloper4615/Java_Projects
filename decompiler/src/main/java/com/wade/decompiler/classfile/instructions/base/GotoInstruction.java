package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {
    public GotoInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, constantPool);
    }
}
