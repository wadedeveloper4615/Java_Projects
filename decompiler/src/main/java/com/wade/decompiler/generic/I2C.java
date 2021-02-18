package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class I2C extends ConversionInstruction {
    public I2C() {
        super(InstructionOpCodes.I2C);
    }
}
