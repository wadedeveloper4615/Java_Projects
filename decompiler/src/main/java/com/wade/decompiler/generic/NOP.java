package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.gen.Visitor;

public class NOP extends Instruction {
    public NOP() {
        super(Const.NOP, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitNOP(this);
    }
}
