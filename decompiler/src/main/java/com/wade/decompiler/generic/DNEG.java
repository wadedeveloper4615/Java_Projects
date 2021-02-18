package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class DNEG extends ArithmeticInstruction {
    public DNEG() {
        super(InstructionOpCodes.DNEG);
    }
}
