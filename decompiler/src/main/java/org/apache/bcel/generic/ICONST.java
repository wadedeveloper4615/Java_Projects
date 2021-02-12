package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.ConstantPushInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class ICONST extends Instruction implements ConstantPushInstruction {
    private int value;

    ICONST() {
    }

    public ICONST(final int i) {
        super(InstructionOpCodes.ICONST_0, (short) 1);
        if ((i >= -1) && (i <= 5)) {
            super.setOpcode(InstructionOpCodes.read((short) (InstructionOpCodes.ICONST_0.getOpcode() + i)));
        } else {
            throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
        }
        value = i;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitICONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.INT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }
}
