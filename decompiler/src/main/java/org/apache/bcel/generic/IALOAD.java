package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.Visitor;

public class IALOAD extends ArrayInstruction implements StackProducer {
    public IALOAD() {
        super(org.apache.bcel.Const.IALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitIALOAD(this);
    }
}
