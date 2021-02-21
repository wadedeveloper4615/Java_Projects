package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class FLOAD extends LoadInstruction {
    public FLOAD(ConstantPool cp) {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0, cp);
    }

    public FLOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.FLOAD, InstructionOpCodes.FLOAD_0, n, cp);
    }
}
