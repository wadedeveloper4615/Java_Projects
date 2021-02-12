package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ConversionInstruction;
import org.apache.bcel.generic.base.Visitor;

public class D2L extends ConversionInstruction {
    public D2L() {
        super(org.apache.bcel.Const.D2L);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitD2L(this);
    }
}
