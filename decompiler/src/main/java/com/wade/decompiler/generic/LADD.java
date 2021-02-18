package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class LADD extends ArithmeticInstruction {
    public LADD() {
        super(InstructionOpCodes.LADD);
    }
}
