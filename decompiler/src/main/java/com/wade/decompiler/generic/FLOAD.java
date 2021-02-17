package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class FLOAD extends LoadInstruction {
    public FLOAD() {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0);
    }

    public FLOAD(int n) {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitFLOAD(this);
    }
}
