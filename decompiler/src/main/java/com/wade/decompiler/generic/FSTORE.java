package com.wade.decompiler.generic;

public class FSTORE extends StoreInstruction {
    FSTORE() {
        super(com.wade.decompiler.Const.FSTORE, com.wade.decompiler.Const.FSTORE_0);
    }

    public FSTORE(final int n) {
        super(com.wade.decompiler.Const.FSTORE, com.wade.decompiler.Const.FSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFSTORE(this);
    }
}
