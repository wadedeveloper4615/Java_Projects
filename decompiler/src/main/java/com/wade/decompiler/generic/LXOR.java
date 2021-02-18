package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class LXOR extends ArithmeticInstruction {
    public LXOR() {
        super(InstructionOpCodes.LXOR);
    }
}
