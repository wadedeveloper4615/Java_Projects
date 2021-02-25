package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class BREAKPOINT extends Instruction {
    public BREAKPOINT(ConstantPool cp) {
        super(InstructionOpCodes.BREAKPOINT, 1, cp);
    }
}