package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.LoadInstruction;

public class FLOAD extends LoadInstruction {
    public FLOAD() {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0);
    }

    public FLOAD(final int n) {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0, n);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        super.accept(v);
//        v.visitFLOAD(this);
//    }
}
