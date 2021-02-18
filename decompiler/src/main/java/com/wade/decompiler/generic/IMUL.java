package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class IMUL extends ArithmeticInstruction {
    public IMUL() {
        super(InstructionOpCodes.IMUL);
    }
}
