package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class F2D extends ConversionInstruction {
    public F2D() {
        super(InstructionOpCodes.F2D);
    }
}
