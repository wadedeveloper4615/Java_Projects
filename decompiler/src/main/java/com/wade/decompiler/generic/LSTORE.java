package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class LSTORE extends StoreInstruction {
    public LSTORE() {
        super(Const.LSTORE, Const.LSTORE_0);
    }

    public LSTORE(final int n) {
        super(Const.LSTORE, Const.LSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLSTORE(this);
    }
}
