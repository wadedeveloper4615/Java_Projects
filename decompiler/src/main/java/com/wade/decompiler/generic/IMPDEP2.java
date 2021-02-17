package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.gen.Visitor;

public class IMPDEP2 extends Instruction {
    public IMPDEP2() {
        super(Const.IMPDEP2, 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitIMPDEP2(this);
    }
}
