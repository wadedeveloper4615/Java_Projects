package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.ConstantPushInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class LCONST extends Instruction implements ConstantPushInstruction {
    private long value;

    LCONST() {
    }

    public LCONST(final long l) {
        super(InstructionOpCodes.LCONST_0, (short) 1);
        if (l == 0) {
            super.setOpcode(InstructionOpCodes.LCONST_0);
        } else if (l == 1) {
            super.setOpcode(InstructionOpCodes.LCONST_1);
        } else {
            throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
        }
        value = l;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitLCONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.LONG;
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }
}
