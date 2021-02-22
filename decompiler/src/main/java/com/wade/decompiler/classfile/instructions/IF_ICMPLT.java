package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPLT extends IfInstruction {
    public IF_ICMPLT(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPLT, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGE(constantPool);
    }
}
