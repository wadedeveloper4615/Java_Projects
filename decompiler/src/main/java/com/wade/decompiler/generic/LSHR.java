package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class LSHR extends ArithmeticInstruction {
    public LSHR() {
        super(InstructionOpCodes.LSHR);
    }
}
