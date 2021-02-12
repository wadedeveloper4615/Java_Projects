package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.StackConsumer;
import org.apache.bcel.generic.base.Visitor;

public class MONITOREXIT extends Instruction implements ExceptionThrower, StackConsumer {
    public MONITOREXIT() {
        super(org.apache.bcel.Const.MONITOREXIT, (short) 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackConsumer(this);
        v.visitMONITOREXIT(this);
    }
}
