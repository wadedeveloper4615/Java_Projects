package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class LADD extends ArithmeticInstruction {
    public LADD() {
        super(InstructionOpCodes.LADD);
    }

    @Override
    public void accept(Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLADD(this);
    }
}
