package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class FSTORE extends StoreInstruction {
    public FSTORE() {
        super(Const.FSTORE, Const.FSTORE_0);
    }

    public FSTORE(final int n) {
        super(Const.FSTORE, Const.FSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFSTORE(this);
    }
}
