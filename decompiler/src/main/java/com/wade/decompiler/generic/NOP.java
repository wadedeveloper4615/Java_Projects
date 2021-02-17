package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.gen.Visitor;

public class NOP extends Instruction {
    public NOP() {
        super(InstructionOpCodes.NOP, 1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitNOP(this);
    }
}
