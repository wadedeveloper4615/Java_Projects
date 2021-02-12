package org.apache.bcel.generic;

import org.apache.bcel.generic.base.LoadInstruction;
import org.apache.bcel.generic.base.Visitor;

public class FLOAD extends LoadInstruction {
    public FLOAD() {
        super(org.apache.bcel.Const.FLOAD, org.apache.bcel.Const.FLOAD_0);
    }

    public FLOAD(final int n) {
        super(org.apache.bcel.Const.FLOAD, org.apache.bcel.Const.FLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFLOAD(this);
    }
}
