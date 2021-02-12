package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.ConstantPushInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class DCONST extends Instruction implements ConstantPushInstruction {
    private double value;

    DCONST() {
    }

    public DCONST(final double f) {
        super(InstructionOpCodes.DCONST_0, (short) 1);
        if (f == 0.0) {
            super.setOpcode(InstructionOpCodes.DCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(InstructionOpCodes.DCONST_1);
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
        return new Double(value);
    }
}
