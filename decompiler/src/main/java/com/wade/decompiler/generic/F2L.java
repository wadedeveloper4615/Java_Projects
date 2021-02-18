package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class F2L extends ConversionInstruction {
    public F2L() {
        super(InstructionOpCodes.F2L);
    }
}
