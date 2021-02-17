package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.UnconditionalBranch;
import com.wade.decompiler.generic.gen.Visitor;

public class ATHROW extends Instruction implements UnconditionalBranch, ExceptionThrower {
    public ATHROW() {
        super(Const.ATHROW, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitUnconditionalBranch(this);
        v.visitExceptionThrower(this);
        v.visitATHROW(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.THROWABLE };
    }
}
