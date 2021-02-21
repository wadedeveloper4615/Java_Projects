package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

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
