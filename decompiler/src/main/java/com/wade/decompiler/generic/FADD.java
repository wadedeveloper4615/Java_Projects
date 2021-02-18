package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class FADD extends ArithmeticInstruction {
    public FADD() {
        super(InstructionOpCodes.FADD);
    }
}
