package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;

public class BREAKPOINT extends Instruction {
    public BREAKPOINT() {
        super(InstructionOpCodes.BREAKPOINT, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitBREAKPOINT(this);
    }
}
