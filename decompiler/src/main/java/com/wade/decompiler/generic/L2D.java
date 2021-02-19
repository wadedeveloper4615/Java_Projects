package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class L2D extends ConversionInstruction {
    public L2D(ConstantPool cp) {
        super(InstructionOpCodes.L2D, cp);
    }
}
