package org.apache.bcel.generic;

import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;

public class IMPDEP1 extends Instruction {
    public IMPDEP1() {
        super(org.apache.bcel.Const.IMPDEP1, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitIMPDEP1(this);
    }
}
