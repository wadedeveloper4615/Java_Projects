package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.FieldInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.inter.PopInstruction;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class PUTSTATIC extends FieldInstruction implements ExceptionThrower, PopInstruction {
    public PUTSTATIC(int index, ConstantPool cp) {
        super(InstructionOpCodes.PUTSTATIC, cp, index);
    }

    @Override
    public int consumeStack() {
        return getFieldSize();
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
