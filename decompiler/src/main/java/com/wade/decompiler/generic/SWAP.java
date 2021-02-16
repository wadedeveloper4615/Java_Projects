package com.wade.decompiler.generic;

public class SWAP extends StackInstruction implements StackConsumer, StackProducer {
    public SWAP() {
        super(com.wade.decompiler.Const.SWAP);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitStackInstruction(this);
        v.visitSWAP(this);
    }
}
