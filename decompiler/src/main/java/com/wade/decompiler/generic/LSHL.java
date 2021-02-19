package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class LSHL extends ArithmeticInstruction {
    public LSHL(ConstantPool cp) {
        super(InstructionOpCodes.LSHL, cp);
    }
}
