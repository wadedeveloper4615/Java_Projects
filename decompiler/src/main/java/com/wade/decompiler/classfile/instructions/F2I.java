package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class F2I extends ConversionInstruction {
    public F2I(ConstantPool cp) {
        super(InstructionOpCodes.F2I, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
