package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.gen.Visitor;

public class BREAKPOINT extends Instruction {
    public BREAKPOINT() {
        super(Const.BREAKPOINT, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitBREAKPOINT(this);
    }
}
