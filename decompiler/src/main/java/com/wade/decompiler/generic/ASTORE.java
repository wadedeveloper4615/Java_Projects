package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class ASTORE extends StoreInstruction {
    public ASTORE() {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0);
    }

    public ASTORE(int n) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, n);
    }
}
