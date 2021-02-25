package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class D2L extends ConversionInstruction {
    public D2L(ConstantPool cp) {
        super(InstructionOpCodes.D2L, cp);
    }
}