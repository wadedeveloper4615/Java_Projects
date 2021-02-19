package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class L2F extends ConversionInstruction {
    public L2F(ConstantPool cp) {
        super(InstructionOpCodes.L2F, cp);
    }
}
