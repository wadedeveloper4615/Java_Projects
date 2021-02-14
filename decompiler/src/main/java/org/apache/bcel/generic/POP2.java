package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.PopInstruction;
import org.apache.bcel.generic.base.StackInstruction;

public class POP2 extends StackInstruction implements PopInstruction {
    public POP2() {
        super(InstructionOpCodes.POP2);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackConsumer(this);
//        v.visitPopInstruction(this);
//        v.visitStackInstruction(this);
//        v.visitPOP2(this);
//    }
}
