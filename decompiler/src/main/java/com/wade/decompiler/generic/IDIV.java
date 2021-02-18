package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;

public class IDIV extends ArithmeticInstruction implements ExceptionThrower {
    public IDIV() {
        super(InstructionOpCodes.IDIV);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ARITHMETIC_EXCEPTION };
    }
}
