package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class ASTORE extends StoreInstruction {
    public ASTORE() {
        super(Const.ASTORE, Const.ASTORE_0);
    }

    public ASTORE(final int n) {
        super(Const.ASTORE, Const.ASTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitASTORE(this);
    }
}
