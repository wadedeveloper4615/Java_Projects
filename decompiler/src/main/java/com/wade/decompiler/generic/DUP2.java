package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2() {
        super(InstructionOpCodes.DUP2);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitStackInstruction(this);
        v.visitDUP2(this);
    }
}
