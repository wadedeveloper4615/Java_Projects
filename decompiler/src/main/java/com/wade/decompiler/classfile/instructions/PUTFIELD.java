package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.FieldInstruction;
import com.wade.decompiler.classfile.instructions.base.PopInstruction;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class PUTFIELD extends FieldInstruction implements PopInstruction, ExceptionThrower {
    public PUTFIELD(int index, ConstantPool cp) {
        super(InstructionOpCodes.PUTFIELD, cp, index);
    }

    @Override
    public int consumeStack() {
        return getFieldSize() + 1;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        Expression exp1 = stack.pop();
        Expression exp2 = stack.pop();
        String result = exp2 + "." + getMethodName() + " = " + exp1 + ";";
        return result;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
