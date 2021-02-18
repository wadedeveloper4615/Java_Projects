package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class FMUL extends ArithmeticInstruction {
    public FMUL() {
        super(InstructionOpCodes.FMUL);
    }
}
