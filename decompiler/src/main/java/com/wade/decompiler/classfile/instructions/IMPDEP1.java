package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IMPDEP1 extends Instruction {
    public IMPDEP1(ConstantPool cp) {
        super(InstructionOpCodes.IMPDEP1, 1, cp);
    }
}
