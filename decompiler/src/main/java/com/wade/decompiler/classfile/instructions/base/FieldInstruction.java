package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class FieldInstruction extends FieldOrMethodInstruction {
    public FieldInstruction(InstructionOpCodes opcode, ConstantPool cp, int index) {
        super(opcode, cp, index);
    }

    public int getFieldSize() {
        return Type.size(Type.getTypeSize(getSignature()));
    }
}
