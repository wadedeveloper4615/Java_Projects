package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ILOAD extends LoadInstruction {
    public ILOAD(ConstantPool cp) {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0, cp);
    }

    public ILOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0, n, cp);
    }
}
