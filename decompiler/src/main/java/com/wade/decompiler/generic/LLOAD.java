package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class LLOAD extends LoadInstruction {
    public LLOAD() {
        super(Const.LLOAD, Const.LLOAD_0);
    }

    public LLOAD(final int n) {
        super(Const.LLOAD, Const.LLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLLOAD(this);
    }
}
