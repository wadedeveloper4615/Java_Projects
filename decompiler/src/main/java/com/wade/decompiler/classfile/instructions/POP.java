package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.PopInstruction;
import com.wade.decompiler.classfile.instructions.base.StackInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

public class POP extends StackInstruction implements PopInstruction {
    public POP(ConstantPool cp) {
        super(InstructionOpCodes.POP, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
