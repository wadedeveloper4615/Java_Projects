package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class ALOAD extends LoadInstruction {
    public ALOAD() {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0);
    }

    public ALOAD(int n) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, n);
    }
}
