package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.StackInstruction;

public class DUP_X1 extends StackInstruction {
    public DUP_X1() {
        super(InstructionOpCodes.DUP_X1);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackInstruction(this);
//        v.visitDUP_X1(this);
//    }
}
