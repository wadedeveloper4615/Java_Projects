package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class F2I extends ConversionInstruction {
    public F2I() {
        super(InstructionOpCodes.F2I);
    }
}
