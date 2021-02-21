package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class I2B extends ConversionInstruction {
    public I2B(ConstantPool cp) {
        super(InstructionOpCodes.I2B, cp);
    }
}
