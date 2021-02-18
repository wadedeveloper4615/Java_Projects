package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class DSTORE extends StoreInstruction {
    public DSTORE() {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0);
    }

    public DSTORE(int n) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, n);
    }
}
