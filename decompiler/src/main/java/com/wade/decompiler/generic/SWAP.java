package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.base.StackProducer;

public class SWAP extends StackInstruction implements StackConsumer, StackProducer {
    public SWAP(ConstantPool cp) {
        super(InstructionOpCodes.SWAP, cp);
    }
}
