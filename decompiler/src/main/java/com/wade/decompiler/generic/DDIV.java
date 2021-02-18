package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class DDIV extends ArithmeticInstruction {
    public DDIV() {
        super(InstructionOpCodes.DDIV);
    }
}
