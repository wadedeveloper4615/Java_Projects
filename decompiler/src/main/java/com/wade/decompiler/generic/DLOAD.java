package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class DLOAD extends LoadInstruction {
    public DLOAD() {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0);
    }

    public DLOAD(int n) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, n);
    }
}
