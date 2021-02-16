package com.wade.decompiler.generic;

public class IMPDEP1 extends Instruction {
    public IMPDEP1() {
        super(com.wade.decompiler.Const.IMPDEP1, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitIMPDEP1(this);
    }
}
