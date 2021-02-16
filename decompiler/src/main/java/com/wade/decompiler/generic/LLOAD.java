package com.wade.decompiler.generic;

public class LLOAD extends LoadInstruction {
    LLOAD() {
        super(com.wade.decompiler.Const.LLOAD, com.wade.decompiler.Const.LLOAD_0);
    }

    public LLOAD(final int n) {
        super(com.wade.decompiler.Const.LLOAD, com.wade.decompiler.Const.LLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLLOAD(this);
    }
}
