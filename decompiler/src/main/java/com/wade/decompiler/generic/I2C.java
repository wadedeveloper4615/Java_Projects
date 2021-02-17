package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ConversionInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class I2C extends ConversionInstruction {
    public I2C() {
        super(Const.I2C);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2C(this);
    }
}
