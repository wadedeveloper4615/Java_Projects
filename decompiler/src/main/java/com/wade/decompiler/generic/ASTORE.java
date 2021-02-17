package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class ASTORE extends StoreInstruction {
    public ASTORE() {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0);
    }

    public ASTORE(int n) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitASTORE(this);
    }
}
