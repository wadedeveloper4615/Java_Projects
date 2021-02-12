package org.apache.bcel.generic;

import org.apache.bcel.generic.base.StoreInstruction;
import org.apache.bcel.generic.base.Visitor;

public class FSTORE extends StoreInstruction {
    public FSTORE() {
        super(org.apache.bcel.Const.FSTORE, org.apache.bcel.Const.FSTORE_0);
    }

    public FSTORE(final int n) {
        super(org.apache.bcel.Const.FSTORE, org.apache.bcel.Const.FSTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFSTORE(this);
    }
}
