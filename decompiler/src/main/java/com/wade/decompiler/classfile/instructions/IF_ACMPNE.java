package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ACMPNE extends IfInstruction {
    public IF_ACMPNE(ConstantPool cp) {
        super(InstructionOpCodes.IF_ACMPNE, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPEQ(constantPool);
    }
}