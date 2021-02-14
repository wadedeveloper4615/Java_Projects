package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.LoadInstruction;

public class LLOAD extends LoadInstruction {
    public LLOAD() {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0);
    }

    public LLOAD(final int n) {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0, n);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        super.accept(v);
//        v.visitLLOAD(this);
//    }
}
