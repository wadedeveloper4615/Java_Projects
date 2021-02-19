package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class I2F extends ConversionInstruction {
    public I2F(ConstantPool cp) {
        super(InstructionOpCodes.I2F, cp);
    }
}
