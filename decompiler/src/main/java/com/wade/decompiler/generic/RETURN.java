package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class RETURN extends ReturnInstruction {
    public RETURN(ConstantPool cp) {
        super(InstructionOpCodes.RETURN, cp);
    }
}
