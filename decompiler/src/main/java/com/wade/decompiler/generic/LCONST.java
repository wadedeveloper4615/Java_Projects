package com.wade.decompiler.generic;

public class LCONST extends Instruction implements ConstantPushInstruction {
    private long value;

    LCONST() {
    }

    public LCONST(final long l) {
        super(com.wade.decompiler.Const.LCONST_0, (short) 1);
        if (l == 0) {
            super.setOpcode(com.wade.decompiler.Const.LCONST_0);
        } else if (l == 1) {
            super.setOpcode(com.wade.decompiler.Const.LCONST_1);
        } else {
            throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
        }
        value = l;
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.LONG;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitLCONST(this);
    }
}
