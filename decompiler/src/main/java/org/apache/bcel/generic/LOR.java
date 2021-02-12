package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArithmeticInstruction;
import org.apache.bcel.generic.base.Visitor;

public class LOR extends ArithmeticInstruction {
    public LOR() {
        super(org.apache.bcel.Const.LOR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLOR(this);
    }
}
