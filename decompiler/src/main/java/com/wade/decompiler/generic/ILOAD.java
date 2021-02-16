package com.wade.decompiler.generic;

public class ILOAD extends LoadInstruction {
    ILOAD() {
        super(com.wade.decompiler.Const.ILOAD, com.wade.decompiler.Const.ILOAD_0);
    }

    public ILOAD(final int n) {
        super(com.wade.decompiler.Const.ILOAD, com.wade.decompiler.Const.ILOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitILOAD(this);
    }
}
