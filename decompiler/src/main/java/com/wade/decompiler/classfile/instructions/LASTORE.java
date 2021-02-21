package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArrayInstruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LASTORE extends ArrayInstruction implements StackConsumer {
    public LASTORE(ConstantPool cp) {
        super(InstructionOpCodes.LASTORE, cp);
    }
}
