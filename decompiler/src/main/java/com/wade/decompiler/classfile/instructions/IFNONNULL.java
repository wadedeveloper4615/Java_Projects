package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFNONNULL extends IfInstruction {
    public IFNONNULL() {
    }

    public IFNONNULL(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFNONNULL, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFNULL(super.getTarget(), constantPool);
    }
}
