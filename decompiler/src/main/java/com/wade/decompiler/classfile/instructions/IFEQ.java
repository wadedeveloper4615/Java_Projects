package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

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
