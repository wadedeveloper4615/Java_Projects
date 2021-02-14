package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.StoreInstruction;

public class ASTORE extends StoreInstruction {
    public ASTORE() {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0);
    }

    public ASTORE(final int n) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, n);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        super.accept(v);
//        v.visitASTORE(this);
//    }
}
