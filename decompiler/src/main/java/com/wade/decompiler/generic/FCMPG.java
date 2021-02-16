package com.wade.decompiler.generic;

public class FCMPG extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public FCMPG() {
        super(com.wade.decompiler.Const.FCMPG, (short) 1);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitFCMPG(this);
    }
}
