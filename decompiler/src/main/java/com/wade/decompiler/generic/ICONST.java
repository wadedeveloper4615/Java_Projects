package com.wade.decompiler.generic;

public class ICONST extends Instruction implements ConstantPushInstruction {
    private int value;

    ICONST() {
    }

    public ICONST(final int i) {
        super(com.wade.decompiler.Const.ICONST_0, (short) 1);
        if ((i >= -1) && (i <= 5)) {
            super.setOpcode((short) (com.wade.decompiler.Const.ICONST_0 + i)); // Even works for i == -1
        } else {
            throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
        }
        value = i;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.INT;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitICONST(this);
    }
}
