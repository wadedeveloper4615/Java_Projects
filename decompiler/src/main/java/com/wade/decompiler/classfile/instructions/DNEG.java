package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArithmeticInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DNEG extends ArithmeticInstruction {
    public DNEG(ConstantPool cp) {
        super(InstructionOpCodes.DNEG, cp);
    }
}
