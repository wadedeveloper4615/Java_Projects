package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.PopInstruction;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class POP extends StackInstruction implements PopInstruction {
    public POP() {
        super(InstructionOpCodes.POP);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitStackInstruction(this);
        v.visitPOP(this);
    }
}
