package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class FREM extends ArithmeticInstruction {
    public FREM() {
        super(InstructionOpCodes.FREM);
    }
}
