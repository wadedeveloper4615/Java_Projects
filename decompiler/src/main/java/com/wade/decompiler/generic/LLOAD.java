package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class LLOAD extends LoadInstruction {
    public LLOAD(ConstantPool cp) {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0, cp);
    }

    public LLOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0, n, cp);
    }
}
