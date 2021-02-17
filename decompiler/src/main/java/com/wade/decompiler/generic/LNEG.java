package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ArithmeticInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class LNEG extends ArithmeticInstruction {
    public LNEG() {
        super(Const.LNEG);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLNEG(this);
    }
}
