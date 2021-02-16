package com.wade.decompiler.generic;

public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
    public ACONST_NULL() {
        super(com.wade.decompiler.Const.ACONST_NULL, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitACONST_NULL(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.NULL;
    }
}
