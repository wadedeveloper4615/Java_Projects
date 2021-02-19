package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class IADD extends ArithmeticInstruction {
    public IADD(ConstantPool cp) {
        super(InstructionOpCodes.IADD, cp);
    }
}
