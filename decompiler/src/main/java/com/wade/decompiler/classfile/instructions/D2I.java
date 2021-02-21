package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConversionInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class D2I extends ConversionInstruction {
    public D2I(ConstantPool cp) {
        super(InstructionOpCodes.D2I, cp);
    }
}
