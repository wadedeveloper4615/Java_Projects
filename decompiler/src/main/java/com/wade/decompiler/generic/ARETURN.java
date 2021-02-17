package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.gen.Visitor;

public class ARETURN extends ReturnInstruction {
    public ARETURN() {
        super(InstructionOpCodes.ARETURN);
    }

    @Override
    public void accept(Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitARETURN(this);
    }
}
