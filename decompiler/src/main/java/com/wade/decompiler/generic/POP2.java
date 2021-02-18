package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.PopInstruction;
import com.wade.decompiler.generic.base.StackInstruction;

public class POP2 extends StackInstruction implements PopInstruction {
    public POP2() {
        super(InstructionOpCodes.POP2);
    }
}
