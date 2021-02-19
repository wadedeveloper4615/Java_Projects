package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackProducer;

public class FALOAD extends ArrayInstruction implements StackProducer {
    public FALOAD(ConstantPool cp) {
        super(InstructionOpCodes.FALOAD, cp);
    }
}
