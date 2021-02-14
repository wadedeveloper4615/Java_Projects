package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ArithmeticInstruction;

public class IXOR extends ArithmeticInstruction {
    public IXOR() {
        super(InstructionOpCodes.IXOR);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitArithmeticInstruction(this);
//        v.visitIXOR(this);
//    }
}
