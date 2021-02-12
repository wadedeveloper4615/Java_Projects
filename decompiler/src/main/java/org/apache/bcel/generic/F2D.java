package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ConversionInstruction;
import org.apache.bcel.generic.base.Visitor;

public class F2D extends ConversionInstruction {
    public F2D() {
        super(org.apache.bcel.Const.F2D);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitF2D(this);
    }
}
