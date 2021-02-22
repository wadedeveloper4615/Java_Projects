package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFEQ extends IfInstruction {
    public IFEQ(ConstantPool cp) {
        super(InstructionOpCodes.IFEQ, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFNE(constantPool);
    }
}
