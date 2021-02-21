package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArithmeticInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class FNEG extends ArithmeticInstruction {
    public FNEG(ConstantPool cp) {
        super(InstructionOpCodes.FNEG, cp);
    }
}
