package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.gen.Visitor;

public class ARRAYLENGTH extends Instruction implements ExceptionThrower, StackProducer, StackConsumer {
    public ARRAYLENGTH() {
        super(InstructionOpCodes.ARRAYLENGTH, 1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitARRAYLENGTH(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }
}
