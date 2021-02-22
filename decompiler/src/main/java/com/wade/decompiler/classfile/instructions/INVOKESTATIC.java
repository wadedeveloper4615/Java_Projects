package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.InvokeInstruction;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

public class INVOKESTATIC extends InvokeInstruction {
    public INVOKESTATIC(int index, ConstantPool cp) {
        super(InstructionOpCodes.INVOKESTATIC, index, cp);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
