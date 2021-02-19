package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConversionInstruction;

public class F2I extends ConversionInstruction {
    public F2I(ConstantPool cp) {
        super(InstructionOpCodes.F2I, cp);
    }
}
