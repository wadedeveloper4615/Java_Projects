package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.LoadInstruction;

public class DLOAD extends LoadInstruction {
    public DLOAD() {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0);
    }

    public DLOAD(final int n) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, n);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        super.accept(v);
//        v.visitDLOAD(this);
//    }
}
