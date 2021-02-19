package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ICMPEQ extends IfInstruction {
    public IF_ICMPEQ() {
    }

    public IF_ICMPEQ(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPEQ, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPNE(super.getTarget(), constantPool);
    }
}
