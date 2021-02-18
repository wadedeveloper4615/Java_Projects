package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class LUSHR extends ArithmeticInstruction {
    public LUSHR() {
        super(InstructionOpCodes.LUSHR);
    }
}
