package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.StoreInstruction;

public class FSTORE extends StoreInstruction {
    public FSTORE() {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0);
    }

    public FSTORE(final int n) {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0, n);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        super.accept(v);
//        v.visitFSTORE(this);
//    }
}
