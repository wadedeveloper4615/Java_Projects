package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

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
