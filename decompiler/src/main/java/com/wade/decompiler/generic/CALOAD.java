package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.gen.Visitor;

public class CALOAD extends ArrayInstruction implements StackProducer {
    public CALOAD() {
        super(InstructionOpCodes.CALOAD);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitCALOAD(this);
    }
}
