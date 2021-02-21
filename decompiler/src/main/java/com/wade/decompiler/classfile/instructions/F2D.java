package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class F2D extends ConversionInstruction {
    public F2D(ConstantPool cp) {
        super(InstructionOpCodes.F2D, cp);
    }
}
