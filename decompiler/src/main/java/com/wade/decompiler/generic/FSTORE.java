package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class FSTORE extends StoreInstruction {
    public FSTORE() {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0);
    }

    public FSTORE(int n) {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitFSTORE(this);
    }
}
