package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFNULL extends IfInstruction {
    public IFNULL(ConstantPool cp) {
        super(InstructionOpCodes.IFNULL, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IfInstruction negate() {
        return new IFNONNULL(constantPool);
    }
}
