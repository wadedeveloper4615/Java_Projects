package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.classfile.ConstantDouble;
import com.wade.decompiler.classfile.ConstantLong;

public class LDC2_W extends CPInstruction implements PushInstruction {
    LDC2_W() {
    }

    public LDC2_W(final int index) {
        super(com.wade.decompiler.Const.LDC2_W, index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitLDC2_W(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cpg) {
        switch (cpg.getConstantPool().getConstant(super.getIndex()).getTag()) {
            case com.wade.decompiler.Const.CONSTANT_Long:
                return Type.LONG;
            case com.wade.decompiler.Const.CONSTANT_Double:
                return Type.DOUBLE;
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + super.getOpcode());
        }
    }

    public Number getValue(final ConstantPoolGen cpg) {
        final Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case Const.CONSTANT_Long:
                return Long.valueOf(((ConstantLong) c).getBytes());
            case Const.CONSTANT_Double:
                return new Double(((ConstantDouble) c).getBytes());
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }
}
