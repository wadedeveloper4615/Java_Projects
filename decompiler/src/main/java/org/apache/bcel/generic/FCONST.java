package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.ConstantPushInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class FCONST extends Instruction implements ConstantPushInstruction {
    private float value;

    FCONST() {
    }

    public FCONST(final float f) {
        super(InstructionOpCodes.FCONST_0, (short) 1);
        if (f == 0.0) {
            super.setOpcode(InstructionOpCodes.FCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(InstructionOpCodes.FCONST_1);
        } else if (f == 2.0) {
            super.setOpcode(InstructionOpCodes.FCONST_2);
        } else {
            throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
        }
        value = f;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitFCONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }

    @Override
    public Number getValue() {
        return new Float(value);
    }
}
