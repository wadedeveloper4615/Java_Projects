package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DLOAD extends LoadInstruction {
    public DLOAD() {
        super(Const.DLOAD, Const.DLOAD_0);
    }

    public DLOAD(final int n) {
        super(Const.DLOAD, Const.DLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDLOAD(this);
    }
}
