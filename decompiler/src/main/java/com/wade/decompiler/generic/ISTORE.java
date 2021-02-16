package com.wade.decompiler.generic;

public class ISTORE extends StoreInstruction {
    ISTORE() {
        super(com.wade.decompiler.Const.ISTORE, com.wade.decompiler.Const.ISTORE_0);
    }

    public ISTORE(final int n) {
        super(com.wade.decompiler.Const.ISTORE, com.wade.decompiler.Const.ISTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitISTORE(this);
    }
}
