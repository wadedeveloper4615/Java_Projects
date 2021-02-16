package com.wade.decompiler.generic;

public class ALOAD extends LoadInstruction {
    ALOAD() {
        super(com.wade.decompiler.Const.ALOAD, com.wade.decompiler.Const.ALOAD_0);
    }

    public ALOAD(final int n) {
        super(com.wade.decompiler.Const.ALOAD, com.wade.decompiler.Const.ALOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitALOAD(this);
    }
}
