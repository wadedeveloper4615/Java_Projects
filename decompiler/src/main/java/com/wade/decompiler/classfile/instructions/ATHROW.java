package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.UnconditionalBranch;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ATHROW extends Instruction implements UnconditionalBranch, ExceptionThrower {
    public ATHROW(ConstantPool cp) {
        super(InstructionOpCodes.ATHROW, 1, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.THROWABLE };
    }
}
