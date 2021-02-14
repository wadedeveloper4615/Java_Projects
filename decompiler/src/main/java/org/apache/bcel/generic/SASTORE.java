package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.StackConsumer;

public class SASTORE extends ArrayInstruction implements StackConsumer {
    public SASTORE() {
        super(InstructionOpCodes.SASTORE);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackConsumer(this);
//        v.visitExceptionThrower(this);
//        v.visitTypedInstruction(this);
//        v.visitArrayInstruction(this);
//        v.visitSASTORE(this);
//    }
}
