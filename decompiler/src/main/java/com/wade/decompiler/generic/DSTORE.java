package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DSTORE extends StoreInstruction {
    public DSTORE() {
        super(Const.DSTORE, Const.DSTORE_0);
    }

    public DSTORE(final int n) {
        super(Const.DSTORE, Const.DSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDSTORE(this);
    }
}
