package com.wade.decompiler.generic;

public class DLOAD extends LoadInstruction {
    DLOAD() {
        super(com.wade.decompiler.Const.DLOAD, com.wade.decompiler.Const.DLOAD_0);
    }

    public DLOAD(final int n) {
        super(com.wade.decompiler.Const.DLOAD, com.wade.decompiler.Const.DLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDLOAD(this);
    }
}
