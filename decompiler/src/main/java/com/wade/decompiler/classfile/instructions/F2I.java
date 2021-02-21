package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class F2I extends ConversionInstruction {
    public F2I(ConstantPool cp) {
        super(InstructionOpCodes.F2I, cp);
    }
}
