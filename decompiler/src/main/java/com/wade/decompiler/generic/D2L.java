package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class D2L extends ConversionInstruction {
    public D2L() {
        super(InstructionOpCodes.D2L);
    }

    @Override
    public void accept(Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitD2L(this);
    }
}
