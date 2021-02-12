package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ConversionInstruction;
import org.apache.bcel.generic.base.Visitor;

public class I2C extends ConversionInstruction {
    public I2C() {
        super(org.apache.bcel.Const.I2C);
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
