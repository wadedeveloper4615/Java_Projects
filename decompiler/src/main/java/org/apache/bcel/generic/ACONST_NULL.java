package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.PushInstruction;
import org.apache.bcel.generic.base.TypedInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
    public ACONST_NULL() {
        super(InstructionOpCodes.ACONST_NULL, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitACONST_NULL(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.NULL;
    }
}
