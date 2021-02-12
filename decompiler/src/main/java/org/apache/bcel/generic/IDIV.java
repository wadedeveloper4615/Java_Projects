package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ArithmeticInstruction;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.Visitor;

public class IDIV extends ArithmeticInstruction implements ExceptionThrower {
    public IDIV() {
        super(InstructionOpCodes.IDIV);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIDIV(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ARITHMETIC_EXCEPTION };
    }
}
