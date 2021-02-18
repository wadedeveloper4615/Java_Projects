package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class I2S extends ConversionInstruction {
    public I2S() {
        super(InstructionOpCodes.I2S);
    }
}
