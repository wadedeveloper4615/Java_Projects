package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class LLOAD extends LoadInstruction {
    public LLOAD() {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0);
    }

    public LLOAD(int n) {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0, n);
    }

    @Override
    public void accept(Visitor v) {
        super.accept(v);
        v.visitLLOAD(this);
    }
}
