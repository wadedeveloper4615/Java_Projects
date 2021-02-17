package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.gen.Visitor;

public class IDIV extends ArithmeticInstruction implements ExceptionThrower {
    public IDIV() {
        super(InstructionOpCodes.IDIV);
    }

    @Override
    public void accept(Visitor v) {
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
