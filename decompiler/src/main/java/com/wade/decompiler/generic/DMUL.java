package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class DMUL extends ArithmeticInstruction {
    public DMUL() {
        super(InstructionOpCodes.DMUL);
    }
}
