package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class ILOAD extends LoadInstruction {
    public ILOAD() {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0);
    }

    public ILOAD(int n) {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitILOAD(this);
    }
}
