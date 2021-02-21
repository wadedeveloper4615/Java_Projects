package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class F2L extends ConversionInstruction {
    public F2L(ConstantPool cp) {
        super(InstructionOpCodes.F2L, cp);
    }
}
