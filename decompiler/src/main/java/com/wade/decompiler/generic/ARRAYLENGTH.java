package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;

public class ARRAYLENGTH extends Instruction implements ExceptionThrower, StackProducer, StackConsumer {
    public ARRAYLENGTH() {
        super(com.wade.decompiler.Const.ARRAYLENGTH, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitARRAYLENGTH(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }
}
