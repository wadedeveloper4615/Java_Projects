package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;

public class IMPDEP2 extends Instruction {
    public IMPDEP2(ConstantPool cp) {
        super(InstructionOpCodes.IMPDEP2, 1, cp);
    }
}
