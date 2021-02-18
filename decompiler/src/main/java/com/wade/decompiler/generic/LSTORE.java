package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class LSTORE extends StoreInstruction {
    public LSTORE() {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0);
    }

    public LSTORE(int n) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, n);
    }
}
