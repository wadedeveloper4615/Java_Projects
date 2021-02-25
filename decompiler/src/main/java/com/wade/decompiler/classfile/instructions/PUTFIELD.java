package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.FieldInstruction;
import com.wade.decompiler.classfile.instructions.base.PopInstruction;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.Utility;

public class PUTFIELD extends FieldInstruction implements PopInstruction, ExceptionThrower {
    public PUTFIELD(int index, ConstantPool cp) {
        super(InstructionOpCodes.PUTFIELD, cp, index);
    }

    @Override
    public int consumeStack() {
        return getFieldSize() + 1;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public String toString() {
        return "Instruction [opcode=" + opcode + "][put value in field = " + Utility.compactClassName(getSuperName()) + "." + getMethodName() + "]";
    }
}
