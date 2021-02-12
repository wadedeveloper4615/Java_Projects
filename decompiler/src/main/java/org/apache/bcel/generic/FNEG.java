package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArithmeticInstruction;
import org.apache.bcel.generic.base.Visitor;

public class FNEG extends ArithmeticInstruction {
    public FNEG() {
        super(org.apache.bcel.Const.FNEG);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFNEG(this);
    }
}
