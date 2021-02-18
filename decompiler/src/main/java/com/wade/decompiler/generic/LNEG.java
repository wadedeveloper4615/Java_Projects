package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class LNEG extends ArithmeticInstruction {
    public LNEG() {
        super(InstructionOpCodes.LNEG);
    }
}
