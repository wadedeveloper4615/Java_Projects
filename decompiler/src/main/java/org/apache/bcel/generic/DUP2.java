package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.PushInstruction;
import org.apache.bcel.generic.base.StackInstruction;

public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2() {
        super(InstructionOpCodes.DUP2);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackProducer(this);
//        v.visitPushInstruction(this);
//        v.visitStackInstruction(this);
//        v.visitDUP2(this);
//    }
}
