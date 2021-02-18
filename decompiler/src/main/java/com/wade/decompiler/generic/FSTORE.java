package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class FSTORE extends StoreInstruction {
    public FSTORE() {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0);
    }

    public FSTORE(int n) {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0, n);
    }
}
