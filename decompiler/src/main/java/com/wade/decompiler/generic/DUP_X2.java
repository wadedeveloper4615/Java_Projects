package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StackInstruction;

public class DUP_X2 extends StackInstruction {
    public DUP_X2() {
        super(InstructionOpCodes.DUP_X2);
    }
}
