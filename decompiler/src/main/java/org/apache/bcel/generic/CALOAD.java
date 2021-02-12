package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.Visitor;

public class CALOAD extends ArrayInstruction implements StackProducer {
    public CALOAD() {
        super(org.apache.bcel.Const.CALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitCALOAD(this);
    }
}
