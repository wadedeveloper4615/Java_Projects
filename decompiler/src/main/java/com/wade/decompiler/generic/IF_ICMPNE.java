package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ICMPNE extends IfInstruction {
    public IF_ICMPNE() {
    }

    public IF_ICMPNE(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPNE, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPEQ(super.getTarget(), constantPool);
    }
}
