package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

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
