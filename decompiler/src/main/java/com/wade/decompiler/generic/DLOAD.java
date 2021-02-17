package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DLOAD extends LoadInstruction {
    public DLOAD() {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0);
    }

    public DLOAD(int n) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitDLOAD(this);
    }
}
