package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ConversionInstruction;
import org.apache.bcel.generic.base.Visitor;

public class I2S extends ConversionInstruction {
    public I2S() {
        super(org.apache.bcel.Const.I2S);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2S(this);
    }
}
