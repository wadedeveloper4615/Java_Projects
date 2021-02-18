package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class D2F extends ConversionInstruction {
    public D2F() {
        super(InstructionOpCodes.D2F);
    }
}
