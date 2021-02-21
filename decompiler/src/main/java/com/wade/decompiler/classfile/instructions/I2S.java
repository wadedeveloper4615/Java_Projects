package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class I2S extends ConversionInstruction {
    public I2S(ConstantPool cp) {
        super(InstructionOpCodes.I2S, cp);
    }
}
