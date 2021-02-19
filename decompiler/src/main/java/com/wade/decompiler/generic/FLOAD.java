package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class FLOAD extends LoadInstruction {
    public FLOAD(ConstantPool cp) {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0, cp);
    }

    public FLOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0, n, cp);
    }
}
