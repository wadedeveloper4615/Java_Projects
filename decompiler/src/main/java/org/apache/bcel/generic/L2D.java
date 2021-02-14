package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ConversionInstruction;

public class L2D extends ConversionInstruction {
    public L2D() {
        super(InstructionOpCodes.L2D);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitConversionInstruction(this);
//        v.visitL2D(this);
//    }
}
