package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class NOP extends Instruction {
    public NOP(ConstantPool cp) {
        super(InstructionOpCodes.NOP, 1, cp);
    }
}