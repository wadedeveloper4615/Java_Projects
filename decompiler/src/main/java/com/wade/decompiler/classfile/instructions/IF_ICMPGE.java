package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPGE extends IfInstruction {
    public IF_ICMPGE() {
    }

    public IF_ICMPGE(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPGE, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLT(super.getTarget(), constantPool);
    }
}
