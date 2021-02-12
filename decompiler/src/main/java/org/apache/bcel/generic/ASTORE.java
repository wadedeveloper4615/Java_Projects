package org.apache.bcel.generic;

import org.apache.bcel.generic.base.StoreInstruction;
import org.apache.bcel.generic.base.Visitor;

public class ASTORE extends StoreInstruction {
    public ASTORE() {
        super(org.apache.bcel.Const.ASTORE, org.apache.bcel.Const.ASTORE_0);
    }

    public ASTORE(final int n) {
        super(org.apache.bcel.Const.ASTORE, org.apache.bcel.Const.ASTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitASTORE(this);
    }
}
