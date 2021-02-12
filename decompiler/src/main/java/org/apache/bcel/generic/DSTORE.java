package org.apache.bcel.generic;

import org.apache.bcel.generic.base.StoreInstruction;
import org.apache.bcel.generic.base.Visitor;

public class DSTORE extends StoreInstruction {
    public DSTORE() {
        super(org.apache.bcel.Const.DSTORE, org.apache.bcel.Const.DSTORE_0);
    }

    public DSTORE(final int n) {
        super(org.apache.bcel.Const.DSTORE, org.apache.bcel.Const.DSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDSTORE(this);
    }
}
