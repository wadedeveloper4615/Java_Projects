package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class IUSHR extends ArithmeticInstruction {
    public IUSHR() {
        super(InstructionOpCodes.IUSHR);
    }
}
