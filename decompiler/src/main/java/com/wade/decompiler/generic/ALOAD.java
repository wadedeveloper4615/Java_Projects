package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.LoadInstruction;

public class ALOAD extends LoadInstruction {
    public ALOAD(ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, cp);
    }

    public ALOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, n, cp);
    }
}
