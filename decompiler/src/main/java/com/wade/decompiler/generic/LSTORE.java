package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class LSTORE extends StoreInstruction {
    public LSTORE() {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0);
    }

    public LSTORE(int n) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitLSTORE(this);
    }
}
