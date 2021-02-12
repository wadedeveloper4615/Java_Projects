package org.apache.bcel.generic;

import org.apache.bcel.generic.base.LoadInstruction;
import org.apache.bcel.generic.base.Visitor;

public class ILOAD extends LoadInstruction {
    public ILOAD() {
        super(org.apache.bcel.Const.ILOAD, org.apache.bcel.Const.ILOAD_0);
    }

    public ILOAD(final int n) {
        super(org.apache.bcel.Const.ILOAD, org.apache.bcel.Const.ILOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitILOAD(this);
    }
}
