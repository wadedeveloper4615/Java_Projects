package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LRETURN extends ReturnInstruction {
    public LRETURN(ConstantPool cp) {
        super(InstructionOpCodes.LRETURN, cp);
    }
}
