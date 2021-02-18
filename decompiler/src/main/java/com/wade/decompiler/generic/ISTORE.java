package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class ISTORE extends StoreInstruction {
    public ISTORE() {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0);
    }

    public ISTORE(int n) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, n);
    }
}
