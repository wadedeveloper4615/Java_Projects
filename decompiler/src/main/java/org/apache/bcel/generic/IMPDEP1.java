package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.Instruction;

public class IMPDEP1 extends Instruction {
    public IMPDEP1() {
        super(InstructionOpCodes.IMPDEP1, (short) 1);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitIMPDEP1(this);
//    }
}
