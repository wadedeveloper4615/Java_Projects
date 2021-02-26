package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPGT extends IfInstruction {
    public IF_ICMPGT(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPGT, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLE(constantPool);
    }
}
