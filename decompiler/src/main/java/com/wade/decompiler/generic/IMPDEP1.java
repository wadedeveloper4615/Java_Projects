package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.gen.Visitor;

public class IMPDEP1 extends Instruction {
    public IMPDEP1() {
        super(Const.IMPDEP1, 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitIMPDEP1(this);
    }
}
