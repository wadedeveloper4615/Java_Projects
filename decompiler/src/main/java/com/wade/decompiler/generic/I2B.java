package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class I2B extends ConversionInstruction {
    public I2B() {
        super(InstructionOpCodes.I2B);
    }

    @Override
    public void accept(Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2B(this);
    }
}
