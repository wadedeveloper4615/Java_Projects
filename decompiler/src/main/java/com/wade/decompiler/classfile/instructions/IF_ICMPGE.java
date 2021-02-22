package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPGE extends IfInstruction {
    public IF_ICMPGE(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPGE, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLT(constantPool);
    }
}
