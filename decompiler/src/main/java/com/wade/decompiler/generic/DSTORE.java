package com.wade.decompiler.generic;

public class DSTORE extends StoreInstruction {
    DSTORE() {
        super(com.wade.decompiler.Const.DSTORE, com.wade.decompiler.Const.DSTORE_0);
    }

    public DSTORE(final int n) {
        super(com.wade.decompiler.Const.DSTORE, com.wade.decompiler.Const.DSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDSTORE(this);
    }
}
