package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ArithmeticInstruction;
import org.apache.bcel.generic.base.Visitor;

public class ISHR extends ArithmeticInstruction {
    public ISHR() {
        super(InstructionOpCodes.ISHR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitISHR(this);
    }
}
