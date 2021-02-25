package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch, TypedInstruction, StackProducer {
    public JsrInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, constantPool);
    }

    @Override
    public Type getType() {
        return null;
    }
}
