package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ALOAD extends LoadInstruction {
    public ALOAD(ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, cp);
    }

    public ALOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, n, cp);
    }
}
