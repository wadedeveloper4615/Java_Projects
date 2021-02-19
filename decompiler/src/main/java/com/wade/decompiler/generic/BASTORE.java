package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackConsumer;

public class BASTORE extends ArrayInstruction implements StackConsumer {
    public BASTORE(ConstantPool cp) {
        super(InstructionOpCodes.BASTORE, cp);
    }
}
