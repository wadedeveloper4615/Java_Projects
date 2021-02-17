package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DSTORE extends StoreInstruction {
    public DSTORE() {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0);
    }

    public DSTORE(int n) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitDSTORE(this);
    }
}
