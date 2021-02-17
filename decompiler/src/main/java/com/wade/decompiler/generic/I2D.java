package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class I2D extends ConversionInstruction {
    public I2D() {
        super(InstructionOpCodes.I2D);
    }

    @Override
    public void accept(Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2D(this);
    }
}
