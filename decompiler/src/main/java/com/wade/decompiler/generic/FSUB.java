package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class FSUB extends ArithmeticInstruction {
    public FSUB() {
        super(InstructionOpCodes.FSUB);
    }
}
