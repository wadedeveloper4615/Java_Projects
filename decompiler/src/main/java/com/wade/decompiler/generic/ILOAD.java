package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class ILOAD extends LoadInstruction {
    public ILOAD(ConstantPool cp) {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0, cp);
    }

    public ILOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.ILOAD, InstructionOpCodes.ILOAD_0, n, cp);
    }
}
