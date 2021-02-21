package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class FRETURN extends ReturnInstruction {
    public FRETURN(ConstantPool cp) {
        super(InstructionOpCodes.FRETURN, cp);
    }
}
