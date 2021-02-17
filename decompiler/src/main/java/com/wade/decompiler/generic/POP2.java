package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.PopInstruction;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class POP2 extends StackInstruction implements PopInstruction {
    public POP2() {
        super(InstructionOpCodes.POP2);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitStackInstruction(this);
        v.visitPOP2(this);
    }
}
