package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.gen.Visitor;

public class BREAKPOINT extends Instruction {
    public BREAKPOINT() {
        super(InstructionOpCodes.BREAKPOINT, 1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitBREAKPOINT(this);
    }
}
