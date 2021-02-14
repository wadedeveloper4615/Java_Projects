package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ArrayInstruction;

public class BALOAD extends ArrayInstruction implements StackProducer {
    public BALOAD() {
        super(InstructionOpCodes.BALOAD);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackProducer(this);
//        v.visitExceptionThrower(this);
//        v.visitTypedInstruction(this);
//        v.visitArrayInstruction(this);
//        v.visitBALOAD(this);
//    }
}
