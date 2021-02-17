package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class FLOAD extends LoadInstruction {
    public FLOAD() {
        super(Const.FLOAD, Const.FLOAD_0);
    }

    public FLOAD(final int n) {
        super(Const.FLOAD, Const.FLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFLOAD(this);
    }
}
