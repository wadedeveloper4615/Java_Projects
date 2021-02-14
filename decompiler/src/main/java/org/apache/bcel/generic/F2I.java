package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ConversionInstruction;

public class F2I extends ConversionInstruction {
    public F2I() {
        super(InstructionOpCodes.F2I);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitConversionInstruction(this);
//        v.visitF2I(this);
//    }
}
