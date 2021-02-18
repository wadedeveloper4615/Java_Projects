package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class D2I extends ConversionInstruction {
    public D2I() {
        super(InstructionOpCodes.D2I);
    }
}
