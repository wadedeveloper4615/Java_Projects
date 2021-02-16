package com.wade.decompiler.generic;

public class DCONST extends Instruction implements ConstantPushInstruction {
    private double value;

    DCONST() {
    }

    public DCONST(final double f) {
        super(com.wade.decompiler.Const.DCONST_0, (short) 1);
        if (f == 0.0) {
            super.setOpcode(com.wade.decompiler.Const.DCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(com.wade.decompiler.Const.DCONST_1);
        } else {
            throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + f);
        }
        value = f;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitDCONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.DOUBLE;
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }
}
