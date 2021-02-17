package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DUP2_X2 extends StackInstruction {
    public DUP2_X2() {
        super(InstructionOpCodes.DUP2_X2);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP2_X2(this);
    }
}
