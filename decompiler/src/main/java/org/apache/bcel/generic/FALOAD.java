
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.Visitor;

public class FALOAD extends ArrayInstruction implements StackProducer {

    public FALOAD() {
        super(org.apache.bcel.Const.FALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitFALOAD(this);
    }
}
