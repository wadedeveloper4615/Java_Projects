package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class L2I extends ConversionInstruction {
    public L2I(ConstantPool cp) {
        super(InstructionOpCodes.L2I, cp);
    }
}