package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArithmeticInstruction;
import org.apache.bcel.generic.base.Visitor;

public class INEG extends ArithmeticInstruction {
    public INEG() {
        super(org.apache.bcel.Const.INEG);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitINEG(this);
    }
}
