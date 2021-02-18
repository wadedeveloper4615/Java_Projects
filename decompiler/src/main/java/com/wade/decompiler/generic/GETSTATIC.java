package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.FieldInstruction;
import com.wade.decompiler.generic.base.PushInstruction;

public class GETSTATIC extends FieldInstruction implements PushInstruction, ExceptionThrower {
    public GETSTATIC() {
    }

    public GETSTATIC(int index) {
        super(InstructionOpCodes.GETSTATIC, index);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public int produceStack(ConstantPool cpg) {
        return getFieldSize(cpg);
    }
}
