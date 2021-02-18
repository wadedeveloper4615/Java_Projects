package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class D2L extends ConversionInstruction {
    public D2L() {
        super(InstructionOpCodes.D2L);
    }
}
