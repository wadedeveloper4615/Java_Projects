package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.gen.Visitor;

public class LASTORE extends ArrayInstruction implements StackConsumer {
    public LASTORE() {
        super(InstructionOpCodes.LASTORE);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitLASTORE(this);
    }
}
