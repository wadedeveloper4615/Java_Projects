package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackProducer;

public class SALOAD extends ArrayInstruction implements StackProducer {
    public SALOAD(ConstantPool cp) {
        super(InstructionOpCodes.SALOAD, cp);
    }
}
