package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.StoreInstruction;
import org.apache.bcel.generic.base.Visitor;

public class LSTORE extends StoreInstruction {
    public LSTORE() {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0);
    }

    public LSTORE(final int n) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLSTORE(this);
    }
}
