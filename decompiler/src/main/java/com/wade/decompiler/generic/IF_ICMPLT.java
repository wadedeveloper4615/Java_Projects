package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IF_ICMPLT extends IfInstruction {
    public IF_ICMPLT() {
    }

    public IF_ICMPLT(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPLT, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGE(super.getTarget(), constantPool);
    }
}
