package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ALOAD extends LoadInstruction {
    public ALOAD(ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, cp);
    }

    public ALOAD(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, n, localVariableTable, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        stack.push(new Expression(ExpressionType.VARIABLE, localVariable.getName()));
        return null;
    }
}
