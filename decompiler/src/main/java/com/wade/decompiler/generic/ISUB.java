package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class ISUB extends ArithmeticInstruction {
    public ISUB() {
        super(InstructionOpCodes.ISUB);
    }
}
