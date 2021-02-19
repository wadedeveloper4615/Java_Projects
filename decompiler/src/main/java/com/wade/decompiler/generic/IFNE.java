package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFNE extends IfInstruction {
    public IFNE() {
    }

    public IFNE(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFNE, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(super.getTarget(), constantPool);
    }
}
