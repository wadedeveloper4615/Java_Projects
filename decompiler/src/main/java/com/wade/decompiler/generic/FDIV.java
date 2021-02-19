package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class FDIV extends ArithmeticInstruction {
    public FDIV(ConstantPool cp) {
        super(InstructionOpCodes.FDIV, cp);
    }
}
