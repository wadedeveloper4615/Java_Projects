package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IMPDEP2 extends Instruction {
    public IMPDEP2(ConstantPool cp) {
        super(InstructionOpCodes.IMPDEP2, 1, cp);
    }
}