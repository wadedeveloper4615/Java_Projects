package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class DUP_X1 extends StackInstruction {
    public DUP_X1() {
        super(Const.DUP_X1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP_X1(this);
    }
}
