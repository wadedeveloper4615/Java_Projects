package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DLOAD extends LoadInstruction {
    public DLOAD(ConstantPool cp) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, cp);
    }

    public DLOAD(int n, ConstantPool cp) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, n, cp);
    }
}
