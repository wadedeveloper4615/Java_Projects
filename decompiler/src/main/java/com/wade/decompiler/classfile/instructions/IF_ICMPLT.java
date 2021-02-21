package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

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
