package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class I2C extends ConversionInstruction {
    public I2C(ConstantPool cp) {
        super(InstructionOpCodes.I2C, cp);
    }
}
