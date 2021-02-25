package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFNE extends IfInstruction {
    public IFNE(ConstantPool cp) {
        super(InstructionOpCodes.IFNE, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(constantPool);
    }
}