package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

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
