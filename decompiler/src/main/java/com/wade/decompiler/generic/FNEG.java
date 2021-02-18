package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class FNEG extends ArithmeticInstruction {
    public FNEG() {
        super(InstructionOpCodes.FNEG);
    }
}
