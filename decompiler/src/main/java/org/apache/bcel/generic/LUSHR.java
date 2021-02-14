package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ArithmeticInstruction;

public class LUSHR extends ArithmeticInstruction {
    public LUSHR() {
        super(InstructionOpCodes.LUSHR);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitArithmeticInstruction(this);
//        v.visitLUSHR(this);
//    }
}
