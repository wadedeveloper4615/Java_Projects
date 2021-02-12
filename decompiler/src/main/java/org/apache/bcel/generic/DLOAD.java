package org.apache.bcel.generic;

import org.apache.bcel.generic.base.LoadInstruction;
import org.apache.bcel.generic.base.Visitor;

public class DLOAD extends LoadInstruction {
    public DLOAD() {
        super(org.apache.bcel.Const.DLOAD, org.apache.bcel.Const.DLOAD_0);
    }

    public DLOAD(final int n) {
        super(org.apache.bcel.Const.DLOAD, org.apache.bcel.Const.DLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDLOAD(this);
    }
}
