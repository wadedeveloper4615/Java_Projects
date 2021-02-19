package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class DLOAD extends LoadInstruction {
    public DLOAD(ConstantPool cp) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, cp);
    }

    public DLOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, n, cp);
    }
}
