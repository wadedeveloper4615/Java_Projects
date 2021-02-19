package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;

public class LREM extends ArithmeticInstruction implements ExceptionThrower {
    public LREM(ConstantPool cp) {
        super(InstructionOpCodes.LREM, cp);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ARITHMETIC_EXCEPTION };
    }
}
