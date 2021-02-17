package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class ISTORE extends StoreInstruction {
    public ISTORE() {
        super(Const.ISTORE, Const.ISTORE_0);
    }

    public ISTORE(final int n) {
        super(Const.ISTORE, Const.ISTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitISTORE(this);
    }
}
