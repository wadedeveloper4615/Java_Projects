package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class I2D extends ConversionInstruction {
    public I2D(ConstantPool cp) {
        super(InstructionOpCodes.I2D, cp);
    }
}
