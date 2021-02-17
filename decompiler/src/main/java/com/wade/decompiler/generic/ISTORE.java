package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class ISTORE extends StoreInstruction {
    public ISTORE() {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0);
    }

    public ISTORE(int n) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitISTORE(this);
    }
}
