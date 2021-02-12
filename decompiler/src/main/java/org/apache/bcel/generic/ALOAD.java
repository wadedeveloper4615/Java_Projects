package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.LoadInstruction;
import org.apache.bcel.generic.base.Visitor;

public class ALOAD extends LoadInstruction {
    public ALOAD() {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0);
    }

    public ALOAD(final int n) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitALOAD(this);
    }
}
