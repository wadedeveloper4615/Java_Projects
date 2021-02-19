package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

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
