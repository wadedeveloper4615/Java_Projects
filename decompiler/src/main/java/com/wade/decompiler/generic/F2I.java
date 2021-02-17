package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class F2I extends ConversionInstruction {
    public F2I() {
        super(InstructionOpCodes.F2I);
    }

    @Override
    public void accept(Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitF2I(this);
    }
}
