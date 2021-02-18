package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class IOR extends ArithmeticInstruction {
    public IOR() {
        super(InstructionOpCodes.IOR);
    }
}
