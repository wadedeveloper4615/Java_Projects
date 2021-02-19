package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ICMPGT extends IfInstruction {
    public IF_ICMPGT() {
    }

    public IF_ICMPGT(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPGT, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLE(super.getTarget(), constantPool);
    }
}
