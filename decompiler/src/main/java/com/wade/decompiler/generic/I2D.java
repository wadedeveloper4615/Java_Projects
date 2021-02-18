package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class I2D extends ConversionInstruction {
    public I2D() {
        super(InstructionOpCodes.I2D);
    }
}
