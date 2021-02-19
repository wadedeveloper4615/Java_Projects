package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ACMPNE extends IfInstruction {
    public IF_ACMPNE() {
    }

    public IF_ACMPNE(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ACMPNE, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPEQ(super.getTarget(), constantPool);
    }
}
