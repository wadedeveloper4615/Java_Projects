package com.wade.decompiler.generic;

public class LSTORE extends StoreInstruction {
    LSTORE() {
        super(com.wade.decompiler.Const.LSTORE, com.wade.decompiler.Const.LSTORE_0);
    }

    public LSTORE(final int n) {
        super(com.wade.decompiler.Const.LSTORE, com.wade.decompiler.Const.LSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLSTORE(this);
    }
}
