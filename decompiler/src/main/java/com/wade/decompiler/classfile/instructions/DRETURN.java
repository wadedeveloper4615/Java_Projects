package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DRETURN extends ReturnInstruction {
    public DRETURN(ConstantPool cp) {
        super(InstructionOpCodes.DRETURN, cp);
    }
}
