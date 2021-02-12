package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.Visitor;

public class AALOAD extends ArrayInstruction implements StackProducer {
    public AALOAD() {
        super(org.apache.bcel.Const.AALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitAALOAD(this);
    }
}
