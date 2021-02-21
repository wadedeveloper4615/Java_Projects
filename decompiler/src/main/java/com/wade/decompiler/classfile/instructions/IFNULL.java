package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFNULL extends IfInstruction {
    public IFNULL() {
    }

    public IFNULL(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFNULL, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFNONNULL(super.getTarget(), constantPool);
    }
}
