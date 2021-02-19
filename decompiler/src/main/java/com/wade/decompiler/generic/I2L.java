package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class I2L extends ConversionInstruction {
    public I2L(ConstantPool cp) {
        super(InstructionOpCodes.I2L, cp);
    }
}
