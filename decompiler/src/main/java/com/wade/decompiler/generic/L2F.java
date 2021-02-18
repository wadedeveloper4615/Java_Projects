package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class L2F extends ConversionInstruction {
    public L2F() {
        super(InstructionOpCodes.L2F);
    }
}
