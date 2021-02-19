package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DRETURN extends ReturnInstruction {
    public DRETURN(ConstantPool cp) {
        super(InstructionOpCodes.DRETURN, cp);
    }
}
