package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackConsumer;

public class DASTORE extends ArrayInstruction implements StackConsumer {
    public DASTORE(ConstantPool cp) {
        super(InstructionOpCodes.DASTORE, cp);
    }
}
