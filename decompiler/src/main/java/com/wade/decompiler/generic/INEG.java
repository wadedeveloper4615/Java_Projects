package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class INEG extends ArithmeticInstruction {
    public INEG(ConstantPool cp) {
        super(InstructionOpCodes.INEG, cp);
    }
}
