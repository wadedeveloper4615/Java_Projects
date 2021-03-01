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
public class I2D extends ConversionInstruction {
    public I2D(ConstantPool cp) {
        super(InstructionOpCodes.I2D, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
