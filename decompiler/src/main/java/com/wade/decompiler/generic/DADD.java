package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class DADD extends ArithmeticInstruction {
    public DADD() {
        super(InstructionOpCodes.DADD);
    }
}
