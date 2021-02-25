package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArrayInstruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.enums.InstructionOpCodes;

public class FASTORE extends ArrayInstruction implements StackConsumer {
    public FASTORE(ConstantPool cp) {
        super(InstructionOpCodes.FASTORE, cp);
    }
}