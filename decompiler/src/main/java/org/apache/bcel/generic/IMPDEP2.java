package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.Instruction;

public class IMPDEP2 extends Instruction {
    public IMPDEP2() {
        super(InstructionOpCodes.IMPDEP2, (short) 1);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitIMPDEP2(this);
//    }
}
