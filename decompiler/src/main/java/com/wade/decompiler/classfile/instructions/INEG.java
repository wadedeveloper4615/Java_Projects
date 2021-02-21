package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArithmeticInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class INEG extends ArithmeticInstruction {
    public INEG(ConstantPool cp) {
        super(InstructionOpCodes.INEG, cp);
    }
}
