package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.StoreInstruction;

public class DSTORE extends StoreInstruction {
    public DSTORE() {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0);
    }

    public DSTORE(final int n) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, n);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        super.accept(v);
//        v.visitDSTORE(this);
//    }
}
