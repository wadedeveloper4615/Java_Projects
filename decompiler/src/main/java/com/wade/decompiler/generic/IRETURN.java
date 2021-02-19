package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IRETURN extends ReturnInstruction {
    public IRETURN(ConstantPool cp) {
        super(InstructionOpCodes.IRETURN, cp);
    }
}
