package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ConversionInstruction;

public class D2L extends ConversionInstruction {
    public D2L() {
        super(InstructionOpCodes.D2L);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitConversionInstruction(this);
//        v.visitD2L(this);
//    }
}
