package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class L2F extends ConversionInstruction {
    public L2F(ConstantPool cp) {
        super(InstructionOpCodes.L2F, cp);
    }
}
