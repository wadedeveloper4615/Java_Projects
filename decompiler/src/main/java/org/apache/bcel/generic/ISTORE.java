package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.StoreInstruction;

public class ISTORE extends StoreInstruction {
    public ISTORE() {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0);
    }

    public ISTORE(final int n) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, n);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        super.accept(v);
//        v.visitISTORE(this);
//    }
}
