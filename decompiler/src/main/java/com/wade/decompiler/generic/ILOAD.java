package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class ILOAD extends LoadInstruction {
    public ILOAD() {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0);
    }

    public ILOAD(int n) {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0, n);
    }
}
