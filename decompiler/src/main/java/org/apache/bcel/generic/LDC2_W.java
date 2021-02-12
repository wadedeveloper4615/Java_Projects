
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.CPInstruction;
import org.apache.bcel.generic.base.PushInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class LDC2_W extends CPInstruction implements PushInstruction {

    public LDC2_W() {
    }

    public LDC2_W(final int index) {
        super(org.apache.bcel.Const.LDC2_W, index);
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
            case CONSTANT_Long:
                return Type.LONG;
            case CONSTANT_Double:
                return Type.DOUBLE;
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + super.getOpcode());
        }
    }

    public Number getValue(final ConstantPoolGen cpg) {
        final org.apache.bcel.classfile.constant.Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case CONSTANT_Long:
                return Long.valueOf(((org.apache.bcel.classfile.constant.ConstantLong) c).getBytes());
            case CONSTANT_Double:
                return new Double(((org.apache.bcel.classfile.constant.ConstantDouble) c).getBytes());
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }
}
