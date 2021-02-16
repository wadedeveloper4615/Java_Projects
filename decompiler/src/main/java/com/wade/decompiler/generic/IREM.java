package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;

public class IREM extends ArithmeticInstruction implements ExceptionThrower {
    public IREM() {
        super(com.wade.decompiler.Const.IREM);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ARITHMETIC_EXCEPTION };
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIREM(this);
    }
}
