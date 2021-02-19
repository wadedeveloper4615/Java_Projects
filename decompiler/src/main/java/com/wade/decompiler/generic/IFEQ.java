package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFEQ extends IfInstruction {
    public IFEQ() {
    }

    public IFEQ(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFEQ, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFNE(super.getTarget(), constantPool);
    }
}
