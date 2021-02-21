package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class I2C extends ConversionInstruction {
    public I2C(ConstantPool cp) {
        super(InstructionOpCodes.I2C, cp);
    }
}
