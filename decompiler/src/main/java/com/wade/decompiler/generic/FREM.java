package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class FREM extends ArithmeticInstruction {
    public FREM(ConstantPool cp) {
        super(InstructionOpCodes.FREM, cp);
    }
}
