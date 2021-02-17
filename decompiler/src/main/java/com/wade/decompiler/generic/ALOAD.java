package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class ALOAD extends LoadInstruction {
    public ALOAD() {
        super(Const.ALOAD, Const.ALOAD_0);
    }

    public ALOAD(final int n) {
        super(Const.ALOAD, Const.ALOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitALOAD(this);
    }
}
