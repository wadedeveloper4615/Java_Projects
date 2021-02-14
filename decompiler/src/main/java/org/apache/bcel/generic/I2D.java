package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ConversionInstruction;

public class I2D extends ConversionInstruction {
    public I2D() {
        super(InstructionOpCodes.I2D);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitConversionInstruction(this);
//        v.visitI2D(this);
//    }
}
