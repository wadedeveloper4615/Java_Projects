package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class L2I extends ConversionInstruction {
    public L2I(ConstantPool cp) {
        super(InstructionOpCodes.L2I, cp);
    }
}
