package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IF_ICMPLE extends IfInstruction {
    public IF_ICMPLE(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPLE, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGT(constantPool);
    }
}
