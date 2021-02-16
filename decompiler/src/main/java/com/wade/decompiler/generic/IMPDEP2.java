package com.wade.decompiler.generic;

public class IMPDEP2 extends Instruction {
    public IMPDEP2() {
        super(com.wade.decompiler.Const.IMPDEP2, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitIMPDEP2(this);
    }
}
