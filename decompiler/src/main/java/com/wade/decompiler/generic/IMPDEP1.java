package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;

public class IMPDEP1 extends Instruction {
    public IMPDEP1(ConstantPool cp) {
        super(InstructionOpCodes.IMPDEP1, 1, cp);
    }
}
