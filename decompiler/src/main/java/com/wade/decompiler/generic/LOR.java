package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class LOR extends ArithmeticInstruction {
    public LOR() {
        super(InstructionOpCodes.LOR);
    }
}
