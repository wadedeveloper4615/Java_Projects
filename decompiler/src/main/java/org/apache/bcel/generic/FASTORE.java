package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.StackConsumer;
import org.apache.bcel.generic.base.Visitor;

public class FASTORE extends ArrayInstruction implements StackConsumer {
    public FASTORE() {
        super(org.apache.bcel.Const.FASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitFASTORE(this);
    }
}
