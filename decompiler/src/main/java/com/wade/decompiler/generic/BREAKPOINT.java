package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;

public class BREAKPOINT extends Instruction {
    public BREAKPOINT() {
        super(InstructionOpCodes.BREAKPOINT, 1);
    }
}
