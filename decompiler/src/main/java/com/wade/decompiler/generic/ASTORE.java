package com.wade.decompiler.generic;

public class ASTORE extends StoreInstruction {
    ASTORE() {
        super(com.wade.decompiler.Const.ASTORE, com.wade.decompiler.Const.ASTORE_0);
    }

    public ASTORE(final int n) {
        super(com.wade.decompiler.Const.ASTORE, com.wade.decompiler.Const.ASTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitASTORE(this);
    }
}
