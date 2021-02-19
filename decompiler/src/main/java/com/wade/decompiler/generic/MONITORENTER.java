package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackConsumer;

public class MONITORENTER extends Instruction implements ExceptionThrower, StackConsumer {
    public MONITORENTER(ConstantPool cp) {
        super(InstructionOpCodes.MONITORENTER, 1, cp);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }
}
