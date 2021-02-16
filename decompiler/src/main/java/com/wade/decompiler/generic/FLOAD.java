package com.wade.decompiler.generic;

public class FLOAD extends LoadInstruction {
    FLOAD() {
        super(com.wade.decompiler.Const.FLOAD, com.wade.decompiler.Const.FLOAD_0);
    }

    public FLOAD(final int n) {
        super(com.wade.decompiler.Const.FLOAD, com.wade.decompiler.Const.FLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFLOAD(this);
    }
}
