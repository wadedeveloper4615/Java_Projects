package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DUP_X1 extends StackInstruction {
    public DUP_X1() {
        super(InstructionOpCodes.DUP_X1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP_X1(this);
    }
}
