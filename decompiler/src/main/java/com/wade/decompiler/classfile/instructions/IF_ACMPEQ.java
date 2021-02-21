package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ACMPEQ extends IfInstruction {
    public IF_ACMPEQ() {
    }

    public IF_ACMPEQ(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ACMPEQ, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPNE(super.getTarget(), constantPool);
    }
}
