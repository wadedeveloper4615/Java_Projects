package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;

public class ARRAYLENGTH extends Instruction implements ExceptionThrower, StackProducer, StackConsumer {
    public ARRAYLENGTH(ConstantPool cp) {
        super(InstructionOpCodes.ARRAYLENGTH, 1, cp);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }
}
