package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArithmeticInstruction;

public class DSUB extends ArithmeticInstruction {
    public DSUB(ConstantPool cp) {
        super(InstructionOpCodes.DSUB, cp);
    }
}
