
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.Visitor;

public class SALOAD extends ArrayInstruction implements StackProducer {

    public SALOAD() {
        super(org.apache.bcel.Const.SALOAD);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitSALOAD(this);
    }
}
