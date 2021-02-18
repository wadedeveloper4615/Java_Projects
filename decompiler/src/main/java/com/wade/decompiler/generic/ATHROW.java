package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.UnconditionalBranch;

public class ATHROW extends Instruction implements UnconditionalBranch, ExceptionThrower {
    public ATHROW() {
        super(InstructionOpCodes.ATHROW, 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.THROWABLE };
    }
}
