package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArrayInstruction;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LALOAD extends ArrayInstruction implements StackProducer {
    public LALOAD(ConstantPool cp) {
        super(InstructionOpCodes.LALOAD, cp);
    }
}
