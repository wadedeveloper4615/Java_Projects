package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPEQ extends IfInstruction {
    public IF_ICMPEQ(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPEQ, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPNE(constantPool);
    }
}
