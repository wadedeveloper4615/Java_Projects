package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.classfile.ConstantDouble;
import com.wade.decompiler.classfile.ConstantLong;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.generic.type.Type;

public class LDC2_W extends CPInstruction implements PushInstruction {
    public LDC2_W() {
    }

    public LDC2_W(int index) {
        super(InstructionOpCodes.LDC2_W, index);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitLDC2_W(this);
    }

    @Override
    public Type getType(ConstantPoolGen cpg) {
        Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case CONSTANT_Long:
                return Type.LONG;
            case CONSTANT_Double:
                return Type.DOUBLE;
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + super.getOpcode());
        }
    }

    public Number getValue(ConstantPoolGen cpg) {
        Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case CONSTANT_Long:
                return Long.valueOf(((ConstantLong) c).getBytes());
            case CONSTANT_Double:
                return Double.valueOf(((ConstantDouble) c).getBytes());
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }
}
