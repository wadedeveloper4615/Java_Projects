package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPLE extends IfInstruction {
    public IF_ICMPLE() {
    }

    public IF_ICMPLE(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPLE, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGT(super.getTarget(), constantPool);
    }
}
