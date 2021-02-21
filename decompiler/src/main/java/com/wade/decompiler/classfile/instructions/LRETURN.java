package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LRETURN extends ReturnInstruction {
    public LRETURN(ConstantPool cp) {
        super(InstructionOpCodes.LRETURN, cp);
    }
}
