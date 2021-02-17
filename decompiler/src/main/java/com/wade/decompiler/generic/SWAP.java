package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.gen.Visitor;

public class SWAP extends StackInstruction implements StackConsumer, StackProducer {
    public SWAP() {
        super(Const.SWAP);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitStackInstruction(this);
        v.visitSWAP(this);
    }
}
