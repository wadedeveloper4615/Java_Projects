package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class ILOAD extends LoadInstruction {
    public ILOAD() {
        super(Const.ILOAD, Const.ILOAD_0);
    }

    public ILOAD(final int n) {
        super(Const.ILOAD, Const.ILOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitILOAD(this);
    }
}
