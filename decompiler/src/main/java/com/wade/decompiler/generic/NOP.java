package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;

public class NOP extends Instruction {
    public NOP(ConstantPool cp) {
        super(InstructionOpCodes.NOP, 1, cp);
    }
}
