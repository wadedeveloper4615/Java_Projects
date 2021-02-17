package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.gen.Visitor;

public class MONITOREXIT extends Instruction implements ExceptionThrower, StackConsumer {
    public MONITOREXIT() {
        super(InstructionOpCodes.MONITOREXIT, 1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackConsumer(this);
        v.visitMONITOREXIT(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }
}
