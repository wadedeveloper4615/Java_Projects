package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ArrayInstruction;

public class AALOAD extends ArrayInstruction implements StackProducer {
    public AALOAD() {
        super(InstructionOpCodes.AALOAD);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackProducer(this);
//        v.visitExceptionThrower(this);
//        v.visitTypedInstruction(this);
//        v.visitArrayInstruction(this);
//        v.visitAALOAD(this);
//    }
}
