package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ARETURN extends ReturnInstruction {
    public ARETURN(ConstantPool cp) {
        super(InstructionOpCodes.ARETURN, cp);
    }
}
