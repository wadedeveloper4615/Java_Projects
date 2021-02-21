package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArrayInstruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.enums.InstructionOpCodes;

public class BASTORE extends ArrayInstruction implements StackConsumer {
    public BASTORE(ConstantPool cp) {
        super(InstructionOpCodes.BASTORE, cp);
    }
}
