
package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.UnconditionalBranch;
import org.apache.bcel.generic.base.Visitor;

public class ATHROW extends Instruction implements UnconditionalBranch, ExceptionThrower {

    public ATHROW() {
        super(org.apache.bcel.Const.ATHROW, (short) 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.THROWABLE };
    }

    @Override
    public void accept(final Visitor v) {
        v.visitUnconditionalBranch(this);
        v.visitExceptionThrower(this);
        v.visitATHROW(this);
    }
}
