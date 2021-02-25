package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPNE extends IfInstruction {
    public IF_ICMPNE(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPNE, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPEQ(constantPool);
    }
}