package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

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
